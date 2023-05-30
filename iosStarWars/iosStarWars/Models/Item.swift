//
//  Item.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/05/28.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct Item: Identifiable, Equatable {
    var id = UUID()
    var name: String
    var date: String
}
