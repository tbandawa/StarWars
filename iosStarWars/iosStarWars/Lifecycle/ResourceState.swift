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
    @Published var resource: Any?
    @Published var error: String?
    
    private var viewModel: ResourceViewModel
    
    init() {
        viewModel = KotlinDependencies.shared.getResourceViewModel()
        viewModel.observeResourceItem { result in
            switch result {
                case _ as ResourceResultLoading:
                    self.resource = nil
                    self.loading = true
                    self.error = nil
                
                case let success as Any:
                    self.resource = success
                    self.loading = false
                    self.error = nil
                
                case let error as ResourceResultError<ErrorResponse>?:
                    self.resource = nil
                    self.loading = false
                    self.error = error?.data!.detail!
                
                default:
                    break
            }
        }
    }
    
    func getResource(resourceUrl: String) {
        let url = URL(string: resourceUrl)!
        let paths = url.path.components(separatedBy: "/")
        viewModel.getResource(resourceType: paths[2], resourceId: Int32(paths[3])!)
    }
    
    deinit {
        viewModel.dispose()
    }
}
