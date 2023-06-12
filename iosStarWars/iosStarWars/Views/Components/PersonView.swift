//
//  PersonView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/06/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import data

struct PersonView: View {
    
    let person: Person
    
    var body: some View {
        List {
            
            Section {
                Text(person.height)
            } header: {
                Text("Height")
            }
            .listRowSeparator(.hidden)
            
            Section {
                Text(person.mass)
            } header: {
                Text("Mass")
            }
            .listRowSeparator(.hidden)
            
            Section {
                Text(person.hair_color)
            } header: {
                Text("Hair Color")
            }
            .listRowSeparator(.hidden)
            
            Section {
                Text(person.skin_color)
            } header: {
                Text("Skin Color")
            }
            .listRowSeparator(.hidden)
            
            Section {
                Text(person.eye_color)
            } header: {
                Text("Eye Color")
            }
            .listRowSeparator(.hidden)
            
            Section {
                Text(person.birth_year)
            } header: {
                Text("DOB")
            }
            .listRowSeparator(.hidden)
            
            Section {
                Text(person.gender)
            } header: {
                Text("Gender")
            }
            .listRowSeparator(.hidden)
            
            Section {
                Text(person.homeworld)
            } header: {
                Text("Home World")
            }
            .listRowSeparator(.hidden)
            
            /*Section {
                ForEach(person.films, id: \.self) { film in
                    Text("\(film)")
                }
            } header: {
                Text("Films")
            }
            .listRowSeparator(.hidden)
            
            Section {
                ForEach(person.species, id: \.self) { species in
                    Text("\(species)")
                }
            } header: {
                Text("Species")
            }
            .listRowSeparator(.hidden)
            
            Section {
                ForEach(person.vehicles, id: \.self) { vehicle in
                    Text("\(vehicle)")
                }
            } header: {
                Text("Vehicles")
            }
            .listRowSeparator(.hidden)
            
            Section {
                ForEach(person.starships, id: \.self) { starship in
                    Text("\(starship)")
                }
            } header: {
                Text("Starships")
            }
            .listRowSeparator(.hidden)*/
            
        }
        .listStyle(.inset)
    }
}

/*struct PersonView_Previews: PreviewProvider {
    
    static var previews: some View {
        PersonView()
    }
}*/
