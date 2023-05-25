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
    var resources: PagedItems?
    
    private var viewModel: StarWarsViewModel
    
    // Start registering observables and
    init() {
        viewModel = KotlinDependencies.shared.getStarWarsViewModel()
        viewModel.observeResourceItems { result in
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
                            self.items?.append(contentsOf: self.mapToItems(resources: people.data!.results))
                            self.resources = PagedItems(
                                count: people.data!.count,
                                next: people.data?.next,
                                previous: people.data?.previous
                            )
                    
                        case let planets as ResourceResultSuccess<BaseResource<Planet>>:
                            self.items?.append(contentsOf: self.mapToItems(resources: planets.data!.results))
                            self.resources = PagedItems(
                                count: planets.data!.count,
                                next: planets.data?.next,
                                previous: planets.data?.previous
                            )
                    
                        case let films as ResourceResultSuccess<BaseResource<Film>>:
                            self.items?.append(contentsOf: self.mapToItems(resources: films.data!.results))
                            self.resources = PagedItems(
                                count: films.data!.count,
                                next: films.data?.next,
                                previous: films.data?.previous
                            )
                    
                        case let starships as ResourceResultSuccess<BaseResource<Starship>>:
                            self.items?.append(contentsOf: self.mapToItems(resources: starships.data!.results))
                            self.resources = PagedItems(
                                count: starships.data!.count,
                                next: starships.data?.next,
                                previous: starships.data?.previous
                            )
                
                        case let vehicles as ResourceResultSuccess<BaseResource<Vehicle>>:
                            self.items?.append(contentsOf: self.mapToItems(resources: vehicles.data!.results))
                            self.resources = PagedItems(
                                count: vehicles.data!.count,
                                next: vehicles.data?.next,
                                previous: vehicles.data?.previous
                            )
                
                        case let species as ResourceResultSuccess<BaseResource<Species>>:
                            self.items?.append(contentsOf: self.mapToItems(resources: species.data!.results))
                            self.resources = PagedItems(
                                count: species.data!.count,
                                next: species.data?.next,
                                previous: species.data?.previous
                            )
                    
                        default:
                            let _ = print(success)
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
    
    /// Retrieves  resources if next is not null
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
                return people.map(
                    {
                        (person) -> Item in
                        Item(name: person.name, date: person.created)
                    })
            case let planets as [Planet]:
                return planets.map(
                    {
                        (planet) -> Item in
                        Item(name: planet.name, date: planet.created)
                    })
            case let films as [Film]:
                return films.map(
                    {
                        (film) -> Item in
                        Item(name: film.title, date: film.created)
                    })
            case let starships as [Starship]:
                return starships.map(
                    {
                        (starship) -> Item in
                        Item(name: starship.name, date: starship.created)
                    })
            case let vehicles as [Vehicle]:
                return vehicles.map(
                    {
                        (vehicle) -> Item in
                        Item(name: vehicle.name, date: vehicle.created)
                    })
            case let species as [Species]:
                return species.map(
                    {
                        (species) -> Item in
                        Item(name: species.name, date: species.created)
                    })
            default:
                return [Item(name: "Name", date: "Date")]
        }
    }
    
    deinit {
        viewModel.dispose()
    }
}

struct PagedItems {
    var count: Int64
    var next: String?
    var previous: String?
}

struct Item: Identifiable, Equatable {
    var id = UUID()
    var name: String
    var date: String
}
