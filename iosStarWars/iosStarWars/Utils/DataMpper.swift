//
//  DataMpper.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/08/06.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import data

extension ObservableObject {
    
    /// Converts array of resources to array of Item
    ///
    ///  - Parameters
    ///     - resources: array of any response resources
    ///  - Returns
    ///     - [Item]: array of Items
    func mapToItems(resources: [Any])-> [Item] {
        if resources.count == 0 {
            return []
        }
        switch resources {
            case let people as [Person]:
                return people.map(
                    {
                        (person) -> Item in
                        Item(name: person.name, type: "people", date: person.edited, url: person.url)
                    })
            
            case let planets as [Planet]:
                return planets.map(
                    {
                        (planet) -> Item in
                        Item(name: planet.name, type: "planets", date: planet.edited, url: planet.url)
                    })
            
            case let films as [Film]:
                return films.map(
                    {
                        (film) -> Item in
                        Item(name: film.title, type: "films", date: film.edited, url: film.url)
                    })
            
            case let starships as [Starship]:
                return starships.map(
                    {
                        (starship) -> Item in
                        Item(name: starship.name, type: "starships", date: starship.edited, url: starship.url)
                    })
            
            case let vehicles as [Vehicle]:
                return vehicles.map(
                    {
                        (vehicle) -> Item in
                        Item(name: vehicle.name, type: "vehicles", date: vehicle.edited, url: vehicle.url)
                    })
            
            case let species as [Species]:
                return species.map(
                    {
                        (species) -> Item in
                        Item(name: species.name, type: "species", date: species.edited, url: species.url)
                    })
            
            default:
            return [Item(name: "Name", type: "Type", date: "Date", url: "Url")]
        }
    }
    
}
