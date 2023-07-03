//
//  SpaceshipView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/06/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import data

struct StarshipView: View {
    
    let starship: Starship
    
    var body: some View {
        
        ScrollView {
            
            LazyVStack(alignment: .leading) {
                
                HStack {
                    
                    VStack {
                        Text("Model")
                            .font(.system(size: 18))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(starship.model)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Manufacturer")
                            .font(.system(size: 18))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(starship.manufacturer)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Cost In Credit")
                            .font(.system(size: 18))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(starship.cost_in_credits)
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
                        Text("Length")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(starship.length)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Max Atmospheric Speed")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(starship.max_atmosphering_speed)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Crew")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(starship.crew)
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
                        Text("Passengers")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(starship.passengers)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Cargo Capacity")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(starship.cargo_capacity)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Consumables")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(starship.consumables)
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
                        Text("Hyperdrive Rating")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(starship.hyperdrive_rating)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("MGLT")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(starship.MGLT)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Starship Class")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(starship.starship_class)
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
                
                if starship.pilots.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("Pilots")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(.black)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(starship.pilots, id: \.self) { pilot in
                            Text("\(pilot)")
                                .padding(5)
                                .font(.system(size: 14, weight: .regular, design: .rounded))
                                .foregroundColor(.white)
                                .background(.black)
                                .cornerRadius(30)
                        }
                    }
                    .padding(.bottom, 15)
                }
                
                if starship.films.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("Films")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(.black)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(starship.films, id: \.self) { film in
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
                
                Text(DateUtils.formatDate(resourceDate: starship.edited))
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
