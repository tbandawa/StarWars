//
//  ResourceState.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/05/25.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

import Foundation
import UIKit
import data

class ResourceState: ObservableObject {
    
    @Published var loading = false
    @Published var resources: Any?
    @Published var error: String?
    
    private var viewModel: ResourceViewModel
    
    init() {
        viewModel = KotlinDependencies.shared.getResourceViewModel()
        viewModel.observeResourceItem { result in
            switch result {
                case _ as ResourceResultLoading:
                    self.resources = nil
                    self.loading = true
                    self.error = nil
                
                case let success as Any:
                    print(success)
                    self.loading = false
                    self.error = nil
                
                case let error as ResourceResultError<ErrorResponse>?:
                    self.resources = nil
                    self.loading = false
                    self.error = error?.data!.detail!
                
                default:
                    break
            }
        }
    }
    
    func getResource(resourceType: String, resourceId: Int32) {
        viewModel.getResource(resourceType: resourceType, resourceId: resourceId)
    }
    
    deinit {
        viewModel.dispose()
    }
}
