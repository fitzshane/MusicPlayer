package BasicPlayer;

import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import Database.Database;

public class BasicPlayer
{
    public static void main(String [] args) throws IOException
    {
        Object [] selection = {"Shuffle", "Play by Title", "Play by Artist", "Add Song", "Remove Song", "Quit"};
        String mainMenuSelection = (String) JOptionPane.showInputDialog(null, "Player","", 1 , null, selection, selection[0]);
        if(mainMenuSelection.equals("Shuffle"))
        {
            Shuffle();
        }
        else if(mainMenuSelection.equals("Play by Title"))
        {
            PlayByTitle();
        }
        else if(mainMenuSelection.equals("Play by Artist"))
        {
            PlayByArtist();
        }
        else if(mainMenuSelection.equals("Add Song"))
        {
            AddSong();
        }
        else if(mainMenuSelection.equals("Remove Song"))
        {
            RemoveSong();
        }
        else if(mainMenuSelection.equals("Quit"))
        {
            Runtime.getRuntime().exec("taskkill /IM wmplayer.exe");
        }
    }

    //shuffles the entire song database and plays it
    public static void Shuffle() throws IOException
    {
        int time = 0;
        String [][]mediaData = Database.getMediaData();
        int index, temp;
        Random random = new Random();
        int shuffle[];
        shuffle = new int[mediaData.length];
        for (int y = 0; y < mediaData.length; y++)
            shuffle[y] = y;
        for (int i = shuffle.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = shuffle[index];
            shuffle[index] = shuffle[i];
            shuffle[i] = temp;
        }
        for (int z = 0; z < mediaData.length; z++)
            System.out.println(shuffle[z]);
        String [][]shuffledMedia = new String[mediaData.length][2];
        for (int z = 0; z < mediaData.length; z++)
        {
            shuffledMedia[z][0] = mediaData[shuffle[z]][0];
            shuffledMedia[z][1] = mediaData[shuffle[z]][4];
        }

        for (int x = 0; x < mediaData.length; x++)
        {
            try
            {
                Runtime.getRuntime().exec("C:\\Program Files\\Windows Media Player\\wmplayer.exe \"C:\\Users\\jtbfi_000\\java\\BasicPlayer\\Media\\" + shuffledMedia[x][0] + "\"");
                time = (Integer.parseInt(shuffledMedia[x][1])*1000);
                System.out.println(time);
                Thread.sleep(time);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
    }

    //displays songs by title
    public static void PlayByTitle()throws IOException
    {
        String [][]mediaData = Database.getMediaData();
        Object []selection = new Object[mediaData.length + 1];
        for (int x = 0; x < mediaData.length; x++)
        {
            selection[x] = mediaData[x][1];
        }
        selection[mediaData.length] = "Quit";
        String mainMenuSelection = (String) JOptionPane.showInputDialog(null, "List of Titles","", 1 , null, selection, selection[0]);
        if(mainMenuSelection.equals("Quit"))
        {
            //Loop
        }
        else
        {
            String selectedSong = "";
            for (int y = 0; y < mediaData.length; y++)
            {
                if (mediaData[y][1].equals(mainMenuSelection))
                    selectedSong = mediaData[y][0];
            }
            //System.out.println();
            Runtime.getRuntime().exec("C:\\Program Files\\Windows Media Player\\wmplayer.exe \"C:\\Users\\jtbfi_000\\java\\BasicPlayer\\Media\\" + selectedSong + "\"");
        }
    }

    //select an artist and gives you the choice to play song
    public static void PlayByArtist()throws IOException
    {
        String [][]mediaData = Database.getMediaData();
        List<String> artistList = new ArrayList<>();
        for (int x = 0; x < mediaData.length; x++)
        {
            artistList.add(mediaData[x][2]);
        }
        artistList = artistList.stream().distinct().collect(Collectors.toList());

        Object []selection = new Object[artistList.size() + 1];
        for (int x = 0; x < artistList.size(); x++)
        {
            selection[x] = artistList.get(x);
        }
        selection[artistList.size()] = "Quit";
        String mainMenuSelection = (String) JOptionPane.showInputDialog(null, "List of Artists","", 1 , null, selection, selection[0]);
        if(mainMenuSelection.equals("Quit"))
        {
            //Loop
        }
        else
        {
            List<String> artistSongList = new ArrayList<>();
            for (int x = 0; x < mediaData.length; x++)
            {
                if (mainMenuSelection.equals(mediaData[x][2]))
                artistSongList.add(mediaData[x][1]);
            }
            Object []selection2 = new Object[artistSongList.size() + 1];
            for (int x = 0; x < artistSongList.size(); x++)
            {
                selection2[x] = artistSongList.get(x);
            }
            selection2[artistSongList.size()] = "Quit";
            String artistsSongSelection = (String) JOptionPane.showInputDialog(null, "Songs by " + mainMenuSelection,"", 1 , null, selection2, selection2[0]);


            String selectedSong = "";
            for (int y = 0; y < mediaData.length; y++)
            {
                if (mediaData[y][1].equals(artistsSongSelection))
                    selectedSong = mediaData[y][0];
            }
            Runtime.getRuntime().exec("C:\\Program Files\\Windows Media Player\\wmplayer.exe \"C:\\Users\\jtbfi_000\\java\\BasicPlayer\\Media\\" + selectedSong + "\"");
        }
    }

    //takes in information and adds to the media file
    public static void AddSong()throws IOException
    {
        String title= JOptionPane.showInputDialog("Please input the song Title");
        String artist= JOptionPane.showInputDialog("Please input the Artist");
        Object [] selection = {"Pop Rock", "Pop", "Metal", "Country", "Jazz"};
        String genre = (String) JOptionPane.showInputDialog(null, "Select the Genre","", 1 , null, selection, selection[0]);
        String length= JOptionPane.showInputDialog("Please input length in Seconds");
        String fileName= JOptionPane.showInputDialog("Please input the Filename");

        String newMedia[] = {fileName, title, artist, genre, length};
        Database.addMedia(newMedia);
    }

    //selects and removes song
    public static void RemoveSong()throws IOException
    {
        String [][]mediaData = Database.getMediaData();
        Object []selection = new Object[mediaData.length + 1];
        for (int x = 0; x < mediaData.length; x++)
        {
            selection[x] = (mediaData[x][1]);
        }
        String songToRemove = (String) JOptionPane.showInputDialog(null, "Player","", 1 , null, selection, selection[0]);
        Database.removeMedia(songToRemove);
    }
}
