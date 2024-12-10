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
    @Published var loading = true
    @Published var error: String?
    @Published var items: [Item]? = []
    
    // Track resource pages
    private var resources: PagedItems?
    
    let viewModel: ResourcesViewModel = DataHelper().resourcesViewModel
    
    // Start registering observables and
    init() {
        viewModel.observeResourceItems{ [self] itemsList in
            self.items = mapToItems(resources: itemsList)
        }
        viewModel.observeResourceResults { result in
            self.loading = true
            // Loop resource availability states
            switch result {
                case let success as ResourceResultSuccess<AnyObject>?:
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
    
    func cancelJob() {
        viewModel.dismissJob()
    }
    
    deinit {
        viewModel.dispose()
    }
}
