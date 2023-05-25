package playlist;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ListIterator;
import java.util.Iterator;
public class Main {

	public static void main(String[] args) {
		ArrayList<album> Albums = new ArrayList<>();

		album Album = new album("Kagayaku Sora no Shijima ni wa", "Dakara Boku wa Ongaku o Yameta");
		Album.addSong("Kagayaku Sora no Shijima ni wa", 4.6);
		Album.addSong("Yura Yura", 4.22);
		Album.addSong("Kuroi Hitsuji", 4.3);
		Album.addSong("Prayer X", 5.6);
		Album.addSong("Kataomi", 3.21);
		Album.addSong("Hoka no Dareka Yori Kanashii Koi o Shita Dake", 6.23);
		Album.addSong("First Love", 4.27);
		Album.addSong("La La La Love Song", 4.2);
		Album.addSong("Yoru ni Kakeru", 3.13);
		Albums.add(Album);

		Album = new album("Gurenge", "Homura");
		Album.addSong("Gurenge", 5.44);
		Album.addSong("Alive", 3.25);
		Album.addSong("Lets go", 3.45);
		Album.addSong("Kataomoi", 3.33);
		Album.addSong("Monster", 4.51);
		Album.addSong("Evil walks", 3.45);
		Album.addSong("Blue bird", 5.25);
		Album.addSong("Kaika Kitan", 5.32);
		Album.addSong("Kokoro no tomo", 5.12);
		Albums.add(Album);

		LinkedList<Song> playList = new LinkedList<Song>();
		Albums.get(0).addToPlayList("You can't do it right", playList);
		Albums.get(0).addToPlayList("True love", playList);
		Albums.get(0).addToPlayList("Forever", playList);  // Does not exist
		Albums.get(0).addToPlayList(9, playList);
		Albums.get(1).addToPlayList(3, playList);
		Albums.get(1).addToPlayList(2, playList);
		Albums.get(1).addToPlayList(24, playList);  // There is no track 24
		
		play(playList);
    }
    private static void play(LinkedList<Song> playList) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;
        ListIterator<Song> listIterator = playList.listIterator();
        if(playList.size() == 0) {
            System.out.println("No songs in playlist");
            return;
        } else {
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }
        while(!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();
            switch(action) {
                case 0:
                    System.out.println("Playlist complete.");
                    quit = true;
                    break;
                case 1:
                    if(!forward) {
                        if(listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if(listIterator.hasNext()) {
                        System.out.println("Now playing " + listIterator.next().toString());
                    } else {
                        System.out.println("We have reached the end of the playlist");
                        forward = false;
                    }
                    break;
                case 2:
                    if(forward) {
                        if(listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if(listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous().toString());
                    } else {
                        System.out.println("We are at the start of the playlist");
                        forward = true;
                    }
                    break;
                case 3:
                    if(forward) {
                        if(listIterator.hasPrevious()) {
                            System.out.println("Now replaying " + listIterator.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the list");
                        }
                    } else {
                        if(listIterator.hasNext()) {
                            System.out.println("Now replaying " + listIterator.next().toString());
                            forward = true;
                        } else {
                            System.out.println("We have reached the end of the list");
                        }
                    }
                    break;
                case 4:
                    printList(playList);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    if(playList.size() >0) {
                        listIterator.remove();
                        if(listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next());
                        } else if(listIterator.hasPrevious()) {
                            System.out.println("Now playing " + listIterator.previous());
                        }
                    }
                    break;
                    
            }
            
        }
        scanner.close();
    }
    private static void printMenu() {
        System.out.println("Available actions:\npress");
        System.out.println("0 - to quit\n" +
                "1 - to play next song\n" +
                "2 - to play previous song\n" +
                "3 - to replay the current song\n" +
                "4 - list songs in the playlist\n" +
                "5 - print available actions.\n" +
                "6 - delete current song from playlist");
    }
    private static void printList(LinkedList<Song> playList) {
        Iterator<Song> iterator = playList.iterator();
        System.out.println("================================");
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("================================");
	}
	}
