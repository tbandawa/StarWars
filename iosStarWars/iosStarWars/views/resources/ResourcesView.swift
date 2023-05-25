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
                    let _ = print(resources)
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
