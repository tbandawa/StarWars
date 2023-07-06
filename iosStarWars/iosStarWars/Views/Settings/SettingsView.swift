//
//  SettingsView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2022/12/01.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SettingsView: View {
    
    @Environment(\.openURL) private var openURL
    
    @State private var isLightTheme = ThemeUtils.instance.getThemeMode()
    
    var themeName: String {
        if (isLightTheme) {
            return "Light"
        } else {
            return "Dark"
        }
    }
    
    var body: some View {
        NavigationView {
            VStack {
                List {
                    Section {
                        HStack {
                            Image(systemName: "paintpalette")
                            Text("Theme")
                            Spacer()
                            Text(themeName)
                                .fontWeight(.light)
                        }
                        .contentShape(Rectangle())
                        .onTapGesture {
                            isLightTheme.toggle()
                            (UIApplication.shared.connectedScenes.first as? UIWindowScene)?.windows.first!.overrideUserInterfaceStyle = isLightTheme ? .light : .dark
                            ThemeUtils.instance.setThemeMode(enable: isLightTheme)
                        }
                    }
                    Section {
                        HStack {
                            Image(systemName: "chevron.left.forwardslash.chevron.right")
                            Text("Github")
                            Spacer()
                            Image(systemName: "chevron.forward")
                        }
                        .contentShape(Rectangle())
                        .onTapGesture {
                            if let url = URL(string:"https://github.com/tbandawa/StarWars") {
                                openURL(url)
                            }
                        }
                        HStack {
                            Image(systemName: "photo")
                            Text("Icons")
                            Spacer()
                            Image(systemName: "chevron.forward")
                        }
                        .contentShape(Rectangle())
                        .onTapGesture {
                            if let url = URL(string: "https://www.svgrepo.com/") {
                                openURL(url)
                            }
                        }
                    }
                }
                .listStyle(.insetGrouped)
            }
            .navigationTitle("Settings")
        }
    }
}

struct SettingsView_Previews: PreviewProvider {
    static var previews: some View {
        SettingsView()
    }
}
