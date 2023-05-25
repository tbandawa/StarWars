//
//  ResourcesState.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/05/25.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

import Foundation
import UIKit
import data

class ResourcesState: ObservableObject {
    
    @Published var loading = false
    @Published var resources: PagedItems?
    @Published var error: String?
    
    private var viewModel: StarWarsViewModel
    
    init() {
        viewModel = KotlinDependencies.shared.getStarWarsViewModel()
        viewModel.observeResourceItems { result in
            switch result {
                case _ as ResourceResultLoading:
                    self.resources = nil
                    self.loading = true
                    self.error = nil
                
                case let success as AnyObject:
                    self.loading = false
                    self.error = nil
                    switch success {
                        case let people as ResourceResultSuccess<BaseResource<Person>>:
                            self.resources = PagedItems(
                                count: people.data!.count,
                                next: people.data?.next,
                                previous: people.data?.previous,
                                items: self.mapToItems(resources: people.data!.results)
                            )
                    
                        case let planets as ResourceResultSuccess<BaseResource<Planet>>:
                            self.resources = PagedItems(
                                count: planets.data!.count,
                                next: planets.data?.next,
                                previous: planets.data?.previous,
                                items: self.mapToItems(resources: planets.data!.results)
                            )
                    
                        case let films as ResourceResultSuccess<BaseResource<Film>>:
                            self.resources = PagedItems(
                                count: films.data!.count,
                                next: films.data?.next,
                                previous: films.data?.previous,
                                items: self.mapToItems(resources: films.data!.results)
                            )
                    
                        case let starships as ResourceResultSuccess<BaseResource<Starship>>:
                            self.resources = PagedItems(
                                count: starships.data!.count,
                                next: starships.data?.next,
                                previous: starships.data?.previous,
                                items: self.mapToItems(resources: starships.data!.results)
                            )
                
                        case let vehicles as ResourceResultSuccess<BaseResource<Vehicle>>:
                            self.resources = PagedItems(
                                count: vehicles.data!.count,
                                next: vehicles.data?.next,
                                previous: vehicles.data?.previous,
                                items: self.mapToItems(resources: vehicles.data!.results)
                            )
                
                        case let species as ResourceResultSuccess<BaseResource<Species>>:
                            self.resources = PagedItems(
                                count: species.data!.count,
                                next: species.data?.next,
                                previous: species.data?.previous,
                                items: self.mapToItems(resources: species.data!.results)
                            )
                    
                        default:
                            let _ = print(success)
                    }
                
                case let error as ResourceResultError<ErrorResponse>:
                    self.resources = nil
                    self.loading = false
                    self.error = error.data!.detail!
                
                default:
                    break
            }
        }
    }
    
    func getResources(resourceType: String, page: Int32) {
        viewModel.getResources(resourceType: resourceType, page: page)
    }
    
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
    let count: Int64
    let next: String?
    let previous: String?
    let items: [Item]
}

struct Item {
    let name: String
    let date: String
}
