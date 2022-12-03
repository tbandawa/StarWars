//
//  SettingsView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2022/12/01.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SettingsView: View {
    var body: some View {
        NavigationView {
            VStack {
                List {
                    Section {
                        HStack {
                            Image(systemName: "paintpalette")
                            Text("Theme")
                            Spacer()
                            Text("Light")
                                .fontWeight(.light)
                        }
                    }
                    Section {
                        HStack {
                            Image(systemName: "ellipsis.curlybraces")
                            Text("Github")
                            Spacer()
                            Image(systemName: "chevron.forward")
                        }
                        HStack {
                            Image(systemName: "photo")
                            Text("Icons")
                            Spacer()
                            Image(systemName: "chevron.forward")
                        }
                    }
                }
                .listStyle(.insetGrouped)
                Spacer()
                Text("Version 1.0.1")
                    .font(.system(size: 14, weight: .light))
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
