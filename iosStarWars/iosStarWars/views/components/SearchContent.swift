//
//  SearchContent.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/05/27.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct SearchContent: View {
    var body: some View {
        VStack {
            ProgressView()
            Text("Loading...")
        }
    }
}

struct SearchContent_Previews: PreviewProvider {
    static var previews: some View {
        SearchContent()
    }
}
