//
//  PlanetView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/06/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import data

struct PlanetView: View {
    
    let planet: Planet
    
    var body: some View {
        
        ScrollView {
            
            LazyVStack(alignment: .leading) {
                
                HStack {
                    
                    VStack {
                        Text("Climate")
                            .font(.system(size: 18))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(planet.climate)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Gravity")
                            .font(.system(size: 18))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(planet.gravity)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Population")
                            .font(.system(size: 18))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(planet.population)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                                    
                }
                
                HStack {
                    
                    VStack {
                        Text("Rotation Period")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(planet.rotation_period)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("orbital Period")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(planet.orbital_period)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Diameter")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(planet.diameter)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                                    
                }
                
                HStack {
                    
                    VStack {
                        Text("Terrain")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(planet.terrain)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Surface Water")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(planet.surface_water)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                                    
                }
                .padding(.bottom, 15)
                
                if planet.films.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("Films")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(.black)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(planet.films, id: \.self) { film in
                            Text("\(film)")
                                .padding(5)
                                .font(.system(size: 14, weight: .regular, design: .rounded))
                                .foregroundColor(.white)
                                .background(.black)
                                .cornerRadius(30)
                        }
                    }
                    .padding(.bottom, 15)
                }
                
                if planet.residents.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("Residents")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(.black)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(planet.residents, id: \.self) { species in
                            Text("\(species)")
                                .padding(5)
                                .font(.system(size: 14, weight: .regular, design: .rounded))
                                .foregroundColor(.white)
                                .background(.black)
                                .cornerRadius(30)
                        }
                    }
                    .padding(.bottom, 15)
                }
                
                Text(planet.created)
                    .font(.system(size: 12))
                    .foregroundColor(.black)
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(5)
                
            }
            .padding(.leading, 16)
            .padding(.trailing, 16)
            
        }
        
    }
}
