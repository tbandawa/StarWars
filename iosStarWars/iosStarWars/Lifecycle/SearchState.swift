//
//  SearchState.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/05/30.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import UIKit
import data

class SearchState: ObservableObject {
    
    // MARK: View Change Properties
    @Published var loading = false
    @Published var error: String?
    @Published var items: [Item]? = []
    @Published var resources: PagedItems? = nil
    
    let viewModel: SearchViewModel = DataHelper().searchViewModel
    
    // Start registering observables
    init() {
        viewModel.observeResourceItems{ itemsList in
            self.items = self.mapToItems(resources: itemsList)
        }
        viewModel.observeResourceResults { result in
            self.loading = true
            self.error = nil
            // Loop resource availability states
            switch result {
                case let success as ResourceResultSuccess<AnyObject>?:
                    self.loading = false
                    self.error = nil
                    //self.loading = false
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
                            let _ = print()
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
    func searchMoreResources(resourceType: String) {
        if let nextUrl = self.resources?.next {
            if let urlComponents = URLComponents(string: nextUrl), let queryItems = urlComponents.queryItems {
                let page = Int32(queryItems[1].value!)!
                let search = queryItems[0].value!
                searchResources(
                    resourceType: resourceType,
                    search: search,
                    page: page
                )
            }
        }
    }
    
    /// Searches  resources
    ///
    /// - Parameters
    ///     - resourceType: type of requested resources
    ///     - page: request page number
    func searchResources(resourceType: String, search: String, page: Int32) {
        if page == 1 {
            self.items = []
        }
        viewModel.searchResources(resourceType: resourceType, search: search, page: page)
    }
    
    func cancelJob() {
        viewModel.dismissJob()
    }
    
    deinit {
        viewModel.dispose()
    }
}
