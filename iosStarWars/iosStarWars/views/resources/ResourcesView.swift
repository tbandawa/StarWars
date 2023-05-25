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
    
    @EnvironmentObject var resourcesState: ResourcesState
    
    var body: some View {
        VStack {
            if let items = resourcesState.items {
                List(items) { item in
                    ItemContent(name: item.name, date: item.date)
                        .onAppear {
                            if (items.last == item) {
                                resourcesState.getMoreResources(resourceType: title.lowercased())
                            }
                        }
                }
            }
            if let errorMessage = resourcesState.error {
                RetryContent(
                    error: errorMessage,
                    retry: {
                        resourcesState.getResources(
                            resourceType: title.lowercased(),
                            page: 1
                        )
                    }
                )
            }
        }
        .overlay {
            if resourcesState.loading {
                LoadingContent()
            }
        }
        .navigationTitle(title)
        .onAppear {
            resourcesState.getResources(
                resourceType: title.lowercased(),
                page: 1
            )
        }
    }
}

struct ResourcesView_Previews: PreviewProvider {
    static var previews: some View {
        ResourcesView(title: "Title")
            .environmentObject(ResourcesState())
    }
}
