package com.example.farooqi.movieapp.data;

import com.example.farooqi.movieapp.R;

import java.util.ArrayList;

/**
 * Created by SAMSUNG on 3/9/2018.
 */

public class FakeData {
    public String title;
    public String description;
    public String image;


    public FakeData(String tle, String des, String img) {
        title = tle;
        description = des;
        image = img;
    }


    private static String[] titles = {"Fast And Furious 8", "The A Team",
            "Planet Of The Apes", "Anabella Creation", "The Mummy"};

    private static String[] descriptons = {
            "Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the destruction of his homeworld and the end of",
            "When a pilot crashes and tells of conflict in the outside world, Diana, an Amazonian warrior in training, leaves home to fight a war, discovering her full powers and true destiny.",
            "The Guardians must fight to keep their newfound family together as they unravel the mystery of Peter Quill's true parentage.",
            "In the near future, a weary Logan cares for an ailing Professor X, somewhere on the Mexican border. However, Logan's attempts to hide from the world, and his legacy, are upended when a young mutant arrives, pursued by dark forces.",
            "Gru meets his long-lost charming, cheerful, and more successful twin brother Dru who wants to team up with him for one last criminal heist."
    };

    private static String[] images = {
            "https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
            "https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
            "https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
            "https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg",
            "https://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg"};

    public static ArrayList<FakeData> getFakeData() {
        ArrayList<FakeData> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add(new FakeData(
                    titles[i], descriptons[i], images[i]));
        }

        return list;
    }

}
