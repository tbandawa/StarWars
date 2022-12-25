//
//  ContentView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2022/12/01.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct ContentView: View {
    
    @ObservedObject private var resourceState: ResourceState = ResourceState()
    
    var body: some View {
        TabView {

            HomeView(
                loading: resourceState.loading,
                resources: resourceState.resources,
                error: resourceState.error,
                loadResources: { resourceState.getResources() },
                retry: { resourceState.getResources() }
            ).tabItem {
                Image(systemName: "star")
                Text("StarWars")
            }
            
            SearchView(
                resources: resourceState.resources
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
