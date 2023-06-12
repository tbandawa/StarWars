//
//  ResourcesState.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/05/25.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import UIKit
import data

class ResourcesState: ObservableObject {
    
    // MARK: View Change Properties
    @Published var loading = false
    @Published var error: String?
    @Published var items: [Item]? = []
    
    // Track resource pages
    private var resources: PagedItems?
    var resourceType = String()
    
    private var viewModel: ResourcesViewModel
    
    // Start registering observables and
    init() {
        viewModel = KotlinDependencies.shared.getResourcesViewModel()
        viewModel.observeResourceItems{ itemsList in
            self.items = self.mapToItems(resources: itemsList)
        }
        viewModel.observeResourceResults { result in
            // Loop resource availability states
            switch result {
                case _ as ResourceResultLoading:
                    self.resources = nil
                    self.loading = true
                    self.error = nil
                
                case let success as AnyObject:
                    self.loading = false
                    self.error = nil
                    // Loop through result, cast to appropiate object,
                    // append to items array and keep base result info
                    switch success {
                        case let people as ResourceResultSuccess<BaseResource<Person>>:
                            self.resources = PagedItems(
                                count: people.data!.count,
                                next: people.data?.next,
                                previous: people.data?.previous
                            )
                    
                        case let planets as ResourceResultSuccess<BaseResource<Planet>>:
                            self.resources = PagedItems(
                                count: planets.data!.count,
                                next: planets.data?.next,
                                previous: planets.data?.previous
                            )
                    
                        case let films as ResourceResultSuccess<BaseResource<Film>>:
                            self.resources = PagedItems(
                                count: films.data!.count,
                                next: films.data?.next,
                                previous: films.data?.previous
                            )
                    
                        case let starships as ResourceResultSuccess<BaseResource<Starship>>:
                            self.resources = PagedItems(
                                count: starships.data!.count,
                                next: starships.data?.next,
                                previous: starships.data?.previous
                            )
                
                        case let vehicles as ResourceResultSuccess<BaseResource<Vehicle>>:
                            self.resources = PagedItems(
                                count: vehicles.data!.count,
                                next: vehicles.data?.next,
                                previous: vehicles.data?.previous
                            )
                
                        case let species as ResourceResultSuccess<BaseResource<Species>>:
                            self.resources = PagedItems(
                                count: species.data!.count,
                                next: species.data?.next,
                                previous: species.data?.previous
                            )
                    
                        default:
                            break
                    }
                
                case let error as ResourceResultError<ErrorResponse>?:
                    self.resources = nil
                    self.loading = false
                    self.error = error?.data!.detail!
                
                default:
                    break
            }
        }
    }
    
    /// Retrieves more resources if next is not null
    ///
    /// - parameter resourceType: type of requested resources
    func getMoreResources(resourceType: String) {
        if let nextUrl = self.resources?.next {
            let page = Int32(nextUrl.components(separatedBy: "page=")[1])
            getResources(resourceType: resourceType, page: page!)
        }
    }
    
    /// Retrieves  resources and clears previous results if page == 1
    ///
    /// - Parameters
    ///     - resourceType: type of requested resources
    ///     - page: request page number
    func getResources(resourceType: String, page: Int32) {
        if (page == 1) {
            self.items = []
        }
        viewModel.getResources(resourceType: resourceType, page: page)
    }
    
    /// Converts array of resources to array of Item
    ///
    ///  - Parameters
    ///     - resources: array of any response resources
    ///  - Returns
    ///     - [Item]: array of Items
    func mapToItems(resources: [Any])-> [Item] {
        switch resources {
            case let people as [Person]:
                self.resourceType = "people"
                return people.map(
                    {
                        (person) -> Item in
                        Item(name: person.name, date: person.created, url: person.url)
                    })
            case let planets as [Planet]:
                self.resourceType = "planets"
                return planets.map(
                    {
                        (planet) -> Item in
                        Item(name: planet.name, date: planet.created, url: planet.url)
                    })
            case let films as [Film]:
                self.resourceType = "films"
                return films.map(
                    {
                        (film) -> Item in
                        Item(name: film.title, date: film.created, url: film.url)
                    })
            case let starships as [Starship]:
                self.resourceType = "starships"
                return starships.map(
                    {
                        (starship) -> Item in
                        Item(name: starship.name, date: starship.created, url: starship.url)
                    })
            case let vehicles as [Vehicle]:
                self.resourceType = "vehicles"
                return vehicles.map(
                    {
                        (vehicle) -> Item in
                        Item(name: vehicle.name, date: vehicle.created, url: vehicle.url)
                    })
            case let species as [Species]:
                self.resourceType = "species"
                return species.map(
                    {
                        (species) -> Item in
                        Item(name: species.name, date: species.created, url: species.url)
                    })
            default:
                return [Item(name: "Name", date: "Date", url: "Url")]
        }
    }
    
    deinit {
        viewModel.dispose()
    }
}
