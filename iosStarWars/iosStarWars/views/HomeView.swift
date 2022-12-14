import SwiftUI
import Foundation

struct HomeView: View {
    
    private var colors: [Color] = [.black, .black, .black]
    private var gridItems = [
        GridItem(.flexible()),
        GridItem(.flexible()),
        GridItem(.flexible())
    ]
    struct CellContent: View {
            var index: Int
            var color: Color
            var body: some View {
                VStack{
                    Text("\(index)")
                        .font(.system(.title))
                        .foregroundColor(.white)
                        .frame(maxWidth: .infinity, alignment: .topLeading)
                        .padding(10)
                    Spacer()
                    Image(systemName: "arrow.right")
                        .resizable()
                        .scaledToFit()
                        .foregroundColor(.white)
                        .frame(width: 30, height: 30)
                        .padding(10)
                        .offset(x: 40)
                }
                .frame(minWidth: 40, maxWidth: .infinity, minHeight: 150)
                .background(color)
                .cornerRadius(8)
            }
        }

    var body: some View {
        NavigationView {
            VStack {
                LazyVGrid(columns: gridItems, spacing: 10) {
                    ForEach((0...5), id: \.self) { index in
                        NavigationLink(destination: ResourcesView()){
                            CellContent(index: index, color: colors[index % colors.count])
                        }
                    }
                }
                Spacer()
            }
            .navigationTitle("StarWars")
            .padding(.leading, 15)
            .padding(.trailing, 15)
        }
	}
}

struct HomeViewView_Previews: PreviewProvider {
	static var previews: some View {
        HomeView()
	}
}
