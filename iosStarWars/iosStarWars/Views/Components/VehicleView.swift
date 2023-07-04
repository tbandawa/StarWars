//
//  VehicleView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/06/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import data

struct VehicleView: View {
    
    let vehicle: Vehicle
    
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
                        Text(vehicle.model)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color("infoBackground"))
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Manufacturer")
                            .font(.system(size: 18))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(vehicle.manufacturer)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color("infoBackground"))
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Cost In Credits")
                            .font(.system(size: 18))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(vehicle.cost_in_credits)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color("infoBackground"))
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
                        Text(vehicle.length)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color("infoBackground"))
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Max Atmospheric Speed")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(vehicle.max_atmosphering_speed)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color("infoBackground"))
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Crew")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(vehicle.crew)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color("infoBackground"))
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
                        Text(vehicle.passengers)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color("infoBackground"))
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Cargo Capacity")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(vehicle.cargo_capacity)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color("infoBackground"))
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Consumables")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(vehicle.consumables)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color("infoBackground"))
                    .cornerRadius(8)
                                    
                }
                
                HStack {
                    
                    VStack {
                        Text("Vehicle Class")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(vehicle.vehicle_class)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color("infoBackground"))
                    .cornerRadius(8)
                                    
                }
                .padding(.bottom, 15)
                
                if vehicle.pilots.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("Pilots")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(Color("infoFont"))
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(vehicle.pilots, id: \.self) { pilot in
                            Text("\(pilot)")
                                .padding(5)
                                .font(.system(size: 14, weight: .regular, design: .rounded))
                                .foregroundColor(.white)
                                .background(Color("infoBackground"))
                                .cornerRadius(30)
                        }
                    }
                    .padding(.bottom, 15)
                }
                
                if vehicle.films.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("Films")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(Color("infoFont"))
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(vehicle.films, id: \.self) { film in
                            Text("\(film)")
                                .padding(5)
                                .font(.system(size: 14, weight: .regular, design: .rounded))
                                .foregroundColor(.white)
                                .background(Color("infoBackground"))
                                .cornerRadius(30)
                        }
                    }
                    .padding(.bottom, 15)
                }
                
                VStack {
                    
                    Text("Last Edited")
                        .font(.system(size: 12, weight: .heavy, design: .default))
                        .foregroundColor(Color("infoFont"))
                        .frame(maxWidth: .infinity, alignment: .leading)
                    
                    Text(DateUtils.formatDate(resourceDate: vehicle.edited))
                        .font(.system(size: 12))
                        .foregroundColor(Color("infoFont"))
                        .frame(maxWidth: .infinity, alignment: .leading)
                        
                }
                .padding(5)
                
            }
            .padding(.leading, 16)
            .padding(.trailing, 16)
            
        }
        
    }
}
