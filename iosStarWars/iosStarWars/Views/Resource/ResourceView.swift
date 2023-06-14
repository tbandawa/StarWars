//
//  ResourceView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2022/12/03.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import data

struct ResourceView: View {
    
    @EnvironmentObject var resourceState: ResourceState
    
    var item: Item
    
    var body: some View {
        ZStack {
                        
            if let resource = resourceState.resource {
                switch resource {
                    //case let person as ResourceResultSuccess<Person>:
                        //PersonView(person: person.data!)
            
                    //case let planet as ResourceResultSuccess<Planet>:
                        //PlanetView(planet: planet.data!)
            
                    //case let film as ResourceResultSuccess<Film>:
                        //FilmView(film: film.data!)
            
                    //case let starship as ResourceResultSuccess<Starship>:
                        //StarshipView(starship: starship.data!)
        
                    //case let vehicle as ResourceResultSuccess<Vehicle>:
                        //VehicleView(vehicle: vehicle.data!)
        
                    case let species as ResourceResultSuccess<Species>:
                        SpeciesView(species: species.data!)
            
                    default:
                        let _ = print()
                }
            }
            
            if resourceState.loading {
                LoadingContent()
            }
            
            if let errorMessage = resourceState.error {
                RetryContent(
                    error: errorMessage,
                    retry: {
                        resourceState.getResource(resourceUrl: item.url)
                    }
                )
            }
            
        }
        .onAppear {
            resourceState.getResource(resourceUrl: item.url)
        }
        .navigationBarTitle(item.name)
    }
}

struct ResourceView_Previews: PreviewProvider {
    static var previews: some View {
        ResourceView(item: Item(name: "name", date: "date", url: "url"))
            .environmentObject(ResourceState())
    }
}
