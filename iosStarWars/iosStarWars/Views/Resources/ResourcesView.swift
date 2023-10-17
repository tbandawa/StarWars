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
    @State private var showingAlert = false
    
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
            
            if resourcesState.loading {
                if (resourcesState.items?.count == 0) {
                    LoadingContent()
                }
            }
            
        }
        .navigationTitle(title)
        .onAppear {
            if ((title.lowercased() != ((resourcesState.items?.count == 0 ? "" : resourcesState.items?[0].type) ?? "")) || (resourcesState.items?.count == 0)) {
                resourcesState.getResources(
                    resourceType: title.lowercased(),
                    page: 1
                )
            }
        }
        .onDisappear {
            if resourcesState.loading {
                resourcesState.cancelJob()
            }
        }
        .alert("Select A Type Of Resource You Would Like To Search", isPresented: $showingAlert) {
            Button("OK", role: .cancel) { }
        }
    }
}

struct ResourcesView_Previews: PreviewProvider {
    static var previews: some View {
        ResourcesView(title: "Title")
            .environmentObject(ResourcesState())
    }
}
