//
//  ResourcesView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2022/12/07.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import data

struct ResourcesView: View {
    
    var title: String
    var resourceUrl: String
    
    @EnvironmentObject var resourcesState: ResourcesState
    
    var body: some View {
        NavigationView {
            VStack {
                if resourcesState.loading {
                    LoadingContent()
                }
                if let resources = resourcesState.resources {
                    switch resources {
                        case let people as ResourceResultSuccess<BaseResource<Person>>:
                            let _ = print(people.data!)
                        
                        case let planets as ResourceResultSuccess<BaseResource<Planet>>:
                            let _ = print(planets.data!)
                        
                        case let films as ResourceResultSuccess<BaseResource<Film>>:
                            let _ = print(films.data!)
                        
                        case let starships as ResourceResultSuccess<BaseResource<Starship>>:
                            let _ = print(starships.data!)
                    
                        case let vehicles as ResourceResultSuccess<BaseResource<Vehicle>>:
                            let _ = print(vehicles.data!)
                    
                        case let species as ResourceResultSuccess<BaseResource<Species>>:
                            let _ = print(species.data!)
                        
                        default:
                            let _ = print(resources)
                    }
                }
                if let errorMessage = resourcesState.error {
                    RetryContent(
                        error: errorMessage,
                        retry: { resourcesState.getResources(resourceType: title.lowercased(), page: 1) }
                    )
                }
            }
            
        }
        .navigationTitle(title)
        .padding(.leading, 15)
        .padding(.trailing, 15)
        .onAppear {
            resourcesState.getResources(resourceType: title.lowercased(), page: 1)
        }
    }
}

struct ResourcesView_Previews: PreviewProvider {
    static var previews: some View {
        ResourcesView(title: "Title", resourceUrl: "url")
            .environmentObject(ResourcesState())
    }
}
