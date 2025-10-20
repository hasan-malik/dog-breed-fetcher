package dogapi;

import java.sql.SQLOutput;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//         MY TESTS:
//        String hasansBreed = "corgi";
//        DogApiBreedFetcher thing = new DogApiBreedFetcher();
//        try {
//            System.out.println("HEY");
//            System.out.println(thing.getSubBreeds(hasansBreed));
//        } catch (Exception e) {
//            System.out.println("Breed not found: " + hasansBreed);
//        }


//        CachingBreedFetcher thing2 = new CachingBreedFetcher(new DogApiBreedFetcher());
//        String answer = "";
//        try {
//            answer = thing2.getSubBreeds(hasansBreed).toString();
//        } catch (Exception e) {
//            System.out.println("Breed not found: " + hasansBreed);
//        }
//
//        System.out.println("here: " + answer);
//        System.out.println(thing2.getCallsMade());
//
//        try {
//            answer = thing2.getSubBreeds("hound").toString();
//        } catch (Exception e) {
//            System.out.println("Breed not found: " + "hound");
//        }
//
//        System.out.println(answer + " " + thing2.getCallsMade());
//
//        try {
//            answer = thing2.getSubBreeds("corgi").toString();
//        } catch (Exception e) {
//            System.out.println("Breed not found: " + "corgi");
//        }
//
//        System.out.println(answer + " " + thing2.getCallsMade());




        String breed = "hound";
        BreedFetcher breedFetcher = new CachingBreedFetcher(new BreedFetcherForLocalTesting());
        int result = getNumberOfSubBreeds(breed, breedFetcher);
        System.out.println(breed + " has " + result + " sub breeds");

        breed = "cat";
        result = getNumberOfSubBreeds(breed, breedFetcher);
        System.out.println(breed + " has " + result + " sub breeds");
    }

    /**
     * Return the number of sub breeds that the given dog breed has according to the
     * provided fetcher.
     * @param breed the name of the dog breed
     * @param breedFetcher the breedFetcher to use
     * @return the number of sub breeds. Zero should be returned if there are no sub breeds
     * returned by the fetcher
     */
    public static int getNumberOfSubBreeds(String breed, BreedFetcher breedFetcher) {
        // Task 3 implement this code so that it is entirely consistent with its provided documentation.
        // return statement included so that the starter code can compile and run.

        Integer answer = 0;

        try {
            answer = breedFetcher.getSubBreeds(breed).size();
        } finally {
            return answer;
        }


    }
}