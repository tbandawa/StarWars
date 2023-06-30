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
    
    let item: Item
    
    var body: some View {
        ZStack {
            
            if let resource = resourceState.resource {
                
                switch item.type {
                    
                    case "people":
                        let person = resource as! ResourceResultSuccess<Person>
                        if "\(type(of: person.data!))" == "DataPerson" {
                            PersonView(person: person.data!)
                        }
                    
                    case "starships":
                        let starship = resource as! ResourceResultSuccess<Starship>
                        if "\(type(of: starship.data!))" == "DataStarship" {
                            StarshipView(starship: starship.data!)
                        }
                    
                    case "planets":
                        let planet = resource as! ResourceResultSuccess<Planet>
                        if "\(type(of: planet.data!))" == "DataPlanet" {
                            PlanetView(planet: planet.data!)
                        }
                            
                    case "species":
                        let species = resource as! ResourceResultSuccess<Species>
                        if "\(type(of: species.data!))" == "DataSpecies" {
                            SpeciesView(species: species.data!)
                        }
                            
                    case "films":
                        let film = resource as! ResourceResultSuccess<Film>
                        if "\(type(of: film.data!))" == "DataFilm" {
                            FilmView(film: film.data!)
                        }
                            
                    case "vehicles":
                        let vehicle = resource as! ResourceResultSuccess<Vehicle>
                        if "\(type(of: vehicle.data!))" == "DataVehicle" {
                            VehicleView(vehicle: vehicle.data!)
                        }
                            
                    default:
                        let _ = print("Unknow Resource Type")
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
        .onDisappear {
            if resourceState.loading {
                resourceState.cancelJob()
            }
        }
        .navigationBarTitle(item.name)
    }
}

struct ResourceView_Previews: PreviewProvider {
    static var previews: some View {
        ResourceView(item: Item(name: "name", type: "type", date: "date", url: "url"))
            .environmentObject(ResourceState())
    }
}
