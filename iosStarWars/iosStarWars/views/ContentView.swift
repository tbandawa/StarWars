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
                 }
            SearchView()
                 .tabItem {
                     Image(systemName: "magnifyingglass")
                 }
            SettingsView()
                 .tabItem {
                     Image(systemName: "gearshape")
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
