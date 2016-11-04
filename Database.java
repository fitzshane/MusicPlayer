package Database;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class Database
{
    //Retrieves all the data from the Medialist file
    public static String[][] getMediaData() throws IOException
    {
        String mediaList = "MediaList.csv";
        String cvsSplitBy = ",";
        String line = "";
        ArrayList<String> mediaFile = new ArrayList<String>();
        ArrayList<String> mediaTitle = new ArrayList<String>();
        ArrayList<String> mediaArtist = new ArrayList<String>();
        ArrayList<String> mediaGenre = new ArrayList<String>();
        ArrayList<String> mediaLength = new ArrayList<String>();

        int arraylength = 0;
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(mediaList));
            while ((line = br.readLine()) != null)
            {
                String[] allData = line.split(cvsSplitBy);
                mediaFile.add(allData[0]);
                mediaTitle.add(allData[1]);
                mediaArtist.add(allData[2]);
                mediaGenre.add(allData[3]);
                mediaLength.add(allData[4]);
                arraylength ++;
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        String [][]mediaData = new String[arraylength][5];
        for (int x = 0; x < arraylength; x++)
        {
            mediaData[x][0] = mediaFile.get(x);
            mediaData[x][1] = mediaTitle.get(x);
            mediaData[x][2] = mediaArtist.get(x);
            mediaData[x][3] = mediaGenre.get(x);
            mediaData[x][4] = mediaLength.get(x);
        }
        return mediaData;
    }

    //Add media to file
    public static void addMedia(String newMedia[]) throws IOException
    {
        String mediaList = "MediaList.csv";
        String cvsSplitBy = ",";
        String line = "";
        ArrayList<String> mediaFile = new ArrayList<String>();
        ArrayList<String> mediaTitle = new ArrayList<String>();
        ArrayList<String> mediaArtist = new ArrayList<String>();
        ArrayList<String> mediaGenre = new ArrayList<String>();
        ArrayList<String> mediaLength = new ArrayList<String>();

        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(mediaList));
            while ((line = br.readLine()) != null)
            {
                String[] allData = line.split(cvsSplitBy);
                mediaFile.add(allData[0]);
                mediaTitle.add(allData[1]);
                mediaArtist.add(allData[2]);
                mediaGenre.add(allData[3]);
                mediaLength.add(allData[4]);
            }
            mediaFile.add(newMedia[0]);
            mediaTitle.add(newMedia[1]);
            mediaArtist.add(newMedia[2]);
            mediaGenre.add(newMedia[3]);
            mediaLength.add(newMedia[4]);

            FileWriter fileW = new FileWriter(mediaList);
            PrintWriter printW = new PrintWriter(fileW);
            for (int i = 0; i < (mediaFile.size()); i++)
            {
                printW.print(mediaFile.get(i) + ",");
                printW.print(mediaTitle.get(i) + ",");
                printW.print(mediaArtist.get(i) + ",");
                printW.print(mediaGenre.get(i) + ",");
                printW.print(mediaLength.get(i) + "\n");
            }
            printW.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    //Removes selected media information.
    public static void removeMedia(String songToRemove) throws IOException
    {
        String mediaList = "MediaList.csv";
        String cvsSplitBy = ",";
        String line = "";
        int remove = 0;
        ArrayList<String> mediaFile = new ArrayList<String>();
        ArrayList<String> mediaTitle = new ArrayList<String>();
        ArrayList<String> mediaArtist = new ArrayList<String>();
        ArrayList<String> mediaGenre = new ArrayList<String>();
        ArrayList<String> mediaLength = new ArrayList<String>();

        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(mediaList));
            while ((line = br.readLine()) != null)
            {
                String[] allData = line.split(cvsSplitBy);
                mediaFile.add(allData[0]);
                mediaTitle.add(allData[1]);
                mediaArtist.add(allData[2]);
                mediaGenre.add(allData[3]);
                mediaLength.add(allData[4]);
            }
            for (int j = 0; j < (mediaFile.size()); j++)
            {
                if (mediaTitle.get(j).equals(songToRemove))
                {
                    System.out.println(mediaTitle.get(j) + j);
                    remove = j;
                }
            }
            mediaFile.remove(remove);
            mediaTitle.remove(remove);
            mediaArtist.remove(remove);
            mediaGenre.remove(remove);
            mediaLength.remove(remove);
            FileWriter fileW = new FileWriter(mediaList);
            PrintWriter printW = new PrintWriter(fileW);
            for (int i = 0; i < (mediaFile.size()); i++)
            {
                printW.print(mediaFile.get(i) + ",");
                printW.print(mediaTitle.get(i) + ",");
                printW.print(mediaArtist.get(i) + ",");
                printW.print(mediaGenre.get(i) + ",");
                printW.print(mediaLength.get(i) + "\n");
            }
            printW.close();
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
