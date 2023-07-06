//
//  ThemeUtils.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/07/06.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

class ThemeUtils {
    
    static var instance = ThemeUtils()
    
    func setThemeMode(enable: Bool) {
        let defaults = UserDefaults.standard
        defaults.set(enable, forKey: "theme")
    }
    
    func getThemeMode() -> Bool {
        let defaults = UserDefaults.standard
        return defaults.bool(forKey: "theme")
    }
}
