//
//  FilmView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/06/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import data

struct FilmView: View {
    
    let film: Film
    
    var body: some View {
    
        ScrollView {
            
            LazyVStack(alignment: .leading) {
                
                HStack {
                    
                    VStack {
                        Spacer()
                        Text(film.opening_crawl)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .center)
                            .padding(5)
                        Spacer()
                    }
                    .background(Color.black)
                    .cornerRadius(8)
                                    
                }
                .padding(.bottom, 2)
                
                HStack {
                    
                    VStack {
                        Text("Episode ID")
                            .font(.system(size: 18))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text("\(film.episode_id)")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color.black)
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Release Daye")
                            .font(.system(size: 18))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(film.release_date)
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
                        Text("Director")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(film.director)
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
                        Text("Producer")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(film.producer)
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
                
                if film.characters.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("Characters")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(.black)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(film.characters, id: \.self) { character in
                            Text("\(character)")
                                .padding(5)
                                .font(.system(size: 14, weight: .regular, design: .rounded))
                                .foregroundColor(.white)
                                .background(.black)
                                .cornerRadius(30)
                        }
                    }
                    .padding(.bottom, 15)
                }
                
                if film.planets.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("Planets")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(.black)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(film.planets, id: \.self) { planet in
                            Text("\(planet)")
                                .padding(5)
                                .font(.system(size: 14, weight: .regular, design: .rounded))
                                .foregroundColor(.white)
                                .background(.black)
                                .cornerRadius(30)
                        }
                    }
                    .padding(.bottom, 15)
                }
                
                if film.species.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("Species")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(.black)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(film.species, id: \.self) { species in
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
                
                if film.vehicles.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("Vehicles")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(.black)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(film.vehicles, id: \.self) { vehicle in
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
                
                if film.starships.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("Starships")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(.black)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(film.starships, id: \.self) { starship in
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
                
                VStack {
                    
                    Text("Last Edited")
                        .font(.system(size: 12, weight: .heavy, design: .default))
                        .foregroundColor(.black)
                        .frame(maxWidth: .infinity, alignment: .leading)
                    
                    Text(DateUtils.formatDate(resourceDate: film.edited))
                        .font(.system(size: 12))
                        .foregroundColor(.black)
                        .frame(maxWidth: .infinity, alignment: .leading)
                        
                }
                .padding(5)
            }
            .padding(.leading, 16)
            .padding(.trailing, 16)
            
        }
        
    }
}
