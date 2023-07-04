//
//  EmptyResultsView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/07/04.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct EmptyResultsView: View {
    var body: some View {
        VStack {
            Image(systemName: "doc.text.magnifyingglass")
                .resizable()
                .frame(width: 45, height: 45)
            Text("No Results Found")
        }
    }
}

struct EmptyResultsView_Previews: PreviewProvider {
    static var previews: some View {
        EmptyResultsView()
    }
}
