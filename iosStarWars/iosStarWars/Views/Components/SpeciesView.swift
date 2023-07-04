//
//  SpeciesView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/06/11.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import data

struct SpeciesView: View {
    
    let species: Species
    
    var body: some View {
        
        ScrollView {
            
            LazyVStack(alignment: .leading) {
                
                HStack {
                    
                    VStack {
                        Text("Classification")
                            .font(.system(size: 18))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(species.classification)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color("infoBackground"))
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Designation")
                            .font(.system(size: 18))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(species.designation)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color("infoBackground"))
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Average Height")
                            .font(.system(size: 18))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(species.average_height)
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
                        Text("Skin Colors")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(species.skin_colors)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color("infoBackground"))
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Hair Colors")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(species.hair_colors)
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color("infoBackground"))
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Eye Colors")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(species.eye_colors)
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
                        Text("Homeworld")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(species.homeworld ?? "")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .bottomTrailing)
                            .padding(5)
                    }
                    .frame(minWidth: 40, maxHeight: 100)
                    .background(Color("infoBackground"))
                    .cornerRadius(8)
                    
                    VStack {
                        Text("Language")
                            .font(.system(size: 20))
                            .foregroundColor(.white)
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                            .padding(5)
                        Spacer()
                        Text(species.language)
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
                
                if species.people.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("People")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(Color("infoFont"))
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(species.people, id: \.self) { person in
                            Text("\(person)")
                                .padding(5)
                                .font(.system(size: 14, weight: .regular, design: .rounded))
                                .foregroundColor(.white)
                                .background(Color("infoBackground"))
                                .cornerRadius(30)
                        }
                    }
                    .padding(.bottom, 15)
                }
                
                if species.films.count > 0 {
                    LazyVStack(alignment: .leading) {
                        Text("Films")
                            .font(.system(size: 20, weight: .heavy, design: .default))
                            .foregroundColor(Color("infoFont"))
                            .frame(maxWidth: .infinity, alignment: .topLeading)
                        ForEach(species.films, id: \.self) { film in
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
                    
                    Text(DateUtils.formatDate(resourceDate: species.edited))
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
