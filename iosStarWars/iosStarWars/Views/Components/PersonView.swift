//
//  PersonView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/06/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import data

struct PersonView: View {
    
    let person: Person
    
    var body: some View {
        
        ScrollView {
            
            LazyVStack(alignment: .leading) {
                
                HStack {
                    
                    VStack {
                        Text("Height")
                            .font(.system(size: 18))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(person.height)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Mass")
                            .font(.system(size: 18))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(person.mass)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Skin Color")
                            .font(.system(size: 18))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(person.skin_color)
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
                        Text("Height")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(person.height)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Mass")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(person.mass)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Skin Color")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(person.skin_color)
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
                        Text("Home World")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(person.homeworld)
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
                
                if person.films.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("Films")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(.black)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(person.films, id: \.self) { film in
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
                
                if person.species.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("Species")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(.black)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(person.species, id: \.self) { species in
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
                
                if person.vehicles.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("Vehicles")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(.black)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(person.vehicles, id: \.self) { vehicle in
                            Text("\(vehicle)")
                                .padding(5)
                                .font(.system(size: 14, weight: .regular, design: .rounded))
                                .foregroundColor(.white)
                                .background(.black)
                                .cornerRadius(30)
                        }
                    }
                    .padding(.bottom, 15)
                }
                
                if person.starships.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("Starships")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(.black)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(person.starships, id: \.self) { starship in
                            Text("\(starship)")
                                .padding(5)
                                .font(.system(size: 14, weight: .regular, design: .rounded))
                                .foregroundColor(.white)
                                .background(.black)
                                .cornerRadius(30)
                        }
                    }
                    .padding(.bottom, 15)
                }
                
                Text(DateUtils.formatDate(resourceDate: person.edited))
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
