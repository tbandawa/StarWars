//
//  ResourceState.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2022/12/20.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import UIKit
import data

class ResourceState: ObservableObject {
    
    @Published var loading = false
    @Published var resources: [String:String]?
    @Published var error: String?
     
    private var viewModel: StarWarsViewModel
    
    init() {
        
        viewModel = KotlinDependencies.shared.getStarWarsViewModel()
        
        viewModel.observeResources { result in
            
            switch result {
                
                case _ as ResourceResultLoading:
                    self.resources = nil
                    self.loading = true
                    self.error = nil
                
                case let success as ResourceResultSuccess<BaseResource>:
                    self.resources = [:]
                    success.data!.iterator().forEach {
                        let resourceKey: String = $0.first! as String
                        let resourceUrl: String = $0.second! as String
                        self.resources![resourceKey] = resourceUrl
                    }
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
    
    deinit {
        viewModel.dispose()
    }
    
    func getResources() {
        viewModel.getResources()
    }
}
