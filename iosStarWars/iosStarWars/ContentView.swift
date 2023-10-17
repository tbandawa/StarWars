//
//  ContentView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2022/12/01.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct ContentView: View {
    
    var body: some View {
        TabView {

            HomeView()
                .tabItem {
                    Image(systemName: "star")
                    Text("StarWars")
                }
            
            SearchView()
                .tabItem {
                    Image(systemName: "magnifyingglass")
                    Text("Search")
                }
                
            
            SettingsView()
                .tabItem {
                    Image(systemName: "gearshape")
                    Text("Settings")
                }
            
        }
        .tint(Color("tabItems"))
        .onAppear {
            let savedTheme = ThemeUtils.instance.getThemeMode()
            (UIApplication.shared.connectedScenes.first as? UIWindowScene)?.windows.first!.overrideUserInterfaceStyle = savedTheme ? .light : .dark
        }
    }
        
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
            .environmentObject(RootResourcesState())
            .environmentObject(ResourcesState())
            .environmentObject(ResourceState())
            .environmentObject(SearchState())
    }
}
