//
//  LoadingContent.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/05/20.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LoadingContent: View {
    
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        VStack {
            ProgressView()
            Text("Loading...")
                .foregroundColor(Color("infoFont"))
        }
    }
}

struct LoadingContent_Previews: PreviewProvider {
    static var previews: some View {
        LoadingContent()
    }
}
