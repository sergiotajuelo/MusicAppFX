package main;

import artist.Artist;
import media.Album;
import media.MediaFile;
import media.Podcast;
import media.Song;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/***
 * Class to manage files and save/load stats.
 * @author Sergio Tajuelo Cabrera
 * @version 1.0
 */
public class FileUtils {
    /***
     * Loads the data from the file where MediaFile objects where saved.
     * @return the array of MediaFile objects stored in media.txt
     */
    public static ArrayList<MediaFile> loadStats() {
        ArrayList<MediaFile> mediaFiles = new ArrayList<MediaFile>();

        try {
            BufferedReader file = new BufferedReader(
                    new FileReader(new File("media.txt")));

            if((new File("media.txt")).exists() && new File("media.txt").length() > 0){
                String line;
                do{
                    line = file.readLine();

                    if(line != null){
                        String[] data = line.split(";");
                        Artist aux = new Artist(data[3], data[4], data[2], data[5]);

                        if(data[0].equals("Song")){
                            mediaFiles.add(new Song(aux, data[1], data[6]));
                        }
                        else if(data[0].equals("Podcast")){
                            mediaFiles.add(new Podcast(aux, data[1], data[6]));
                        }
                        else if(data[0].equals("Album")){
                            ArrayList<Song> songs = new ArrayList<Song>();

                            String[] songData = data[6].split(",");

                            for (int i = 0; i < songData.length; i+=2) {
                                songs.add(new Song(aux, songData[i], songData[i+1]));
                            }
                            mediaFiles.add(new Album(songs, aux, data[1]));
                        }
                    }
                }
                while (line != null);
                file.close();
            }
            else {
                mediaFiles = new ArrayList<MediaFile>();
            }
        }
        catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return mediaFiles;
    }

    /***
     * Loads the data from the file where Artists objects where saved.
     * @return the array of Artist objects stored in artists.dat
     */
    public static ArrayList<Artist> loadArtists() {
        ArrayList<Artist> artists = new ArrayList<Artist>();

        try {
            BufferedReader file = new BufferedReader(
                    new FileReader(new File("artists.dat")));

            if((new File("artists.dat")).exists()) {
                String line;
                do {
                    line = file.readLine();

                    if (line != null) {
                        String[] data = line.split(";");

                        artists.add(new Artist(data[1], data[2], data[3], data[4]));
                    }
                }
                while (line != null);
                file.close();
            }
            else {
                artists = new ArrayList<Artist>();
            }
        }
        catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
        return artists;
    }

    /***
     * Saves the MediaFile objects list with an specific format in media.txt, overwriting everything.
     * @param arrayList that we want to store.
     */
    public static void saveStats(ArrayList<MediaFile> arrayList) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new BufferedWriter(
                    new FileWriter("media.txt", false)));

            int i = 0;
            while (i < arrayList.size()) {
                MediaFile mediaFile = arrayList.get(i);

                if(mediaFile instanceof Song){
                    printWriter.println("Song;"+ mediaFile.getName() + ";" + mediaFile.getCreator().getArtisticName()
                            + ";" + mediaFile.getCreator().getRealName() + ";" +
                            mediaFile.getCreator().getRealLastName() + ";" + mediaFile.getCreator().getDescription() +
                            ";" + ((Song) mediaFile).getAudioFile());
                }
                else if(mediaFile instanceof Album){
                    printWriter.print("Album;" + mediaFile.getName() + ";" + mediaFile.getCreator().getArtisticName()
                            + ";" + mediaFile.getCreator().getRealName() + ";" +
                            mediaFile.getCreator().getRealLastName() + ";" + mediaFile.getCreator().getDescription() +
                            ";");
                    for (Song s : ((Album)mediaFile).getSongs()) {
                        printWriter.print(s.getName() + "," + s.getAudioFile() + ",");
                    }
                    printWriter.println();
                }
                else if(mediaFile instanceof Podcast){
                    printWriter.println("Podcast;" + mediaFile.getName() + ";" + mediaFile.getCreator().getArtisticName()
                            + ";" + mediaFile.getCreator().getRealName() + ";" +
                            mediaFile.getCreator().getRealLastName() + ";" + mediaFile.getCreator().getDescription()  +
                            ";" + ((Podcast) mediaFile).getAudioFile());
                }
                i++;
            }
            printWriter.close();
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /***
     * Saves the Artist objects list with an specific format in media.txt, overwriting everything.
     * @param artistArrayList that we want to store.
     */
    public static void saveArtists(ArrayList<Artist> artistArrayList){
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new BufferedWriter(
                    new FileWriter("artists.dat", false)));

            int i = 0;
            while (i < artistArrayList.size()) {
                Artist artist = artistArrayList.get(i);

                printWriter.println("Artist;"+ artist.getRealName() + ";" + artist.getRealLastName()
                        + ";" + artist.getArtisticName() + ";" + artist.getDescription());
                i++;
            }
            printWriter.close();
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}