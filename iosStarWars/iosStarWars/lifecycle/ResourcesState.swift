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

class ResourcesState: ObservableObject {
    
    @Published var loading = false
    @Published var resources: [String:String]?
    @Published var error: String?
     
    private var viewModel: StarWarsViewModel
    
    init() {
        
        viewModel = KotlinDependencies.shared.getStarWarsViewModel()
        
        viewModel.observeRootResources { result in
            
            switch result {
                
                case _ as ResourceResultLoading:
                    self.resources = nil
                    self.loading = true
                    self.error = nil
                
                case let success as ResourceResultSuccess<RootResource>:
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
        
        viewModel.observeResourceItems { result in
            
            switch result {
                
                case _ as ResourceResultLoading:
                    print("Loading...")
                
                case let success as ResourceResultSuccess<Person>:
                    print(success.data!)
                
                case let error as ResourceResultError<ErrorResponse>:
                    print(error.data!.detail!)
                
                default:
                    break
                    
            }
        }
        
        viewModel.observeResourceItem { result in
            
            switch result {
                
                case _ as ResourceResultLoading:
                    print("Loading...")
                
                case let success as ResourceResultSuccess<Vehicle>:
                    print(success.data!)
                
                case let error as ResourceResultError<ErrorResponse>:
                    print(error.data!.detail!)
                
                default:
                    break
                    
            }
        }
        
    }
    
    deinit {
        viewModel.dispose()
    }
    
    func getResources() {
        //viewModel.getRootResources()
        viewModel.getResources(resourceType: "people", page: 1)
        //viewModel.getResource(resourceType: "vehicles", resourceId: 4)
    }
}
