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
        ZStack {
            if let items = resourcesState.items {
                List(items) { item in
                    ItemContent(name: item.name, date: item.date)
                        .listRowSeparator(.hidden)
                        .onAppear {
                            if (items.last == item) {
                                resourcesState.getMoreResources(resourceType: title.lowercased())
                            }
                        }
                        .background( NavigationLink("", destination: ResourceView(item: item)).opacity(0) )
                }
                .onAppear {
                    print("onAppear() List \(title.lowercased()) vs \(resourcesState.resources!.type)")
                }
            }
            if resourcesState.loading {
                if (resourcesState.items?.count == 0) {
                    LoadingContent()
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
        .navigationTitle(title)
        .onAppear {
            print("\n")
            print("onAppear() \(title.lowercased()) vs \(resourcesState.resources?.type)")
            print("\n")
            if let items = resourcesState.items {
                if (items.count == 0 && title.lowercased() != resourcesState.resources?.type) {
                    resourcesState.getResources(
                        resourceType: title.lowercased(),
                        page: 1
                    )
                } else {
                    //print("\n")
                    //print("-----------------------------------------------------------not (items.count == 0 && title != resourcesState.resources?.type)")
                    //print("\n")
                }
            } else {
                //print("\n")
                //print("-----------------------------------------------------------items are null")
                //print("\n")
            }
        }
    }
}

struct ResourcesView_Previews: PreviewProvider {
    static var previews: some View {
        ResourcesView(title: "Title")
            .environmentObject(ResourcesState())
    }
}
