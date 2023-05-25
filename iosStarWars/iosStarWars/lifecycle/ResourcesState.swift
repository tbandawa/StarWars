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
    @Published var resources: Any?
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
                    self.resources = success
                    self.loading = false
                    self.error = nil
                
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
    
    deinit {
        viewModel.dispose()
    }
}
