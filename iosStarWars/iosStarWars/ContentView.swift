//
//  ContentView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2022/12/01.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct ContentView: View {
    
    @ObservedObject private var rootResourcesState: RootResourcesState = RootResourcesState()
    
    var body: some View {
        TabView {

            HomeView(
                loading: rootResourcesState.loading,
                resources: rootResourcesState.resources,
                error: rootResourcesState.error,
                loadResources: { rootResourcesState.getRootResources() },
                retry: { rootResourcesState.getRootResources() }
            ).tabItem {
                Image(systemName: "star")
                Text("StarWars")
            }
            
            SearchView(
                resources: rootResourcesState.resources
            ).tabItem {
                Image(systemName: "magnifyingglass")
                Text("Search")
            }
            
            SettingsView()
                .tabItem {
                    Image(systemName: "gearshape")
                    Text("Settings")
                }
            
        }
        .accentColor(.black)
    }
        
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
