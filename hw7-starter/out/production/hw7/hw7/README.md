# Discussion

Approaches for Hash Maps:
    For the chaining hash map, I decided to use a normal array as my auxillary data structure and the elements of that
array were ArrayLists of Pairs (a class which I made that stored a key-value pair). I made each "chain" an ArrayList
because I knew that most of its core operations (such as add to end of list, get, and set) run in O(1) time. My approach for
inserting was fairly simple: first find the index where the key should be added, if that space is null, then add an ArrayList
and the pair we want into that space, if the space already contains a chain, add Pair to chain. Then, if this new addition
puts our load factor over the limit we specify, then rehash(). For rehash(), I simply put the map into a temp variable,
get the new capacity using a table of primes, wipe map clean and make it with new size, and then for each chain in temp and each pair 
in those chains, insert that pair into bigger map we just made.
    For the open addressing hash map, I decided to again use a normal array as my auxillary data structure, but this time
I had each element be a Pair (which has a key, value, and tombstone variables). The tombstone variable is intially set to false,
but if a Pair is removed, then that variable is set to true. I made sure that when calling has(), a tombstone Pair would not
be considered as mapped but when I was inserting, if a tombstone was found, we overwrite it as long as the key we are inserting
is not currently mapped elsewhere. For inserting, if there was a collision, I used linear probing to find new index and like
I mentioned before I allowed overwriting tombstones since I performed a search that the key I was inserting was not already
in the map. My rehash() method was very similar to chaining as again I used a temp map to store my original map, but this time
I only needed to go through all the Pairs in the temp and I inserted every Pair that was not null or a tombstone into the new,
bigger map. 

I originally profiled chaining hash map and open addressing hash map with my original load factors of 0.8 and 0.65,
respectfully. I choose a larger load factor for chaining since it doesn't need to rehash as much as open since it doesn't 
have clustering as there is no probing. 
Chaining Test with 0.8 load factor: 
    Time results: 
        apache.txt = 131.532 ms/op
        jhu.txt = 0.155 ms/op
        joanne.txt = 0.060 ms/op
        newegg.txt = 67.242 ms/op
        random164.txt = 489.108 ms/op
    Space results:
        apache.txt = 176,517,488 bytes
        jhu.txt = 560,103,424 bytes
        joanne.txt = 24,847,436 bytes
        newegg.txt = 75,806,416 bytes
        random164.txt = 394,385,856 bytes
Open Addressing Test with 0.65 load factor: 
    Time results:
        apache.txt = 133.341 ms/op
        jhu.txt = 0.271 ms/op
        joanne.txt = 0.363 ms/op
        newegg.txt = 11343.292 ms/op
        random164.txt = 501.583 ms/op 
    Space results:
        apache.txt = 110,350,287 bytes
        jhu.txt = 23,601,732 bytes
        joanne.txt = 22,542,384 bytes
        newegg.txt = 57,461,016 bytes
        random164.txt = 230,642,891 bytes

I then decided to test both implementations with load factor of 0.75. 
    Chaining Time results:
    Chaining Space results:
    
    Open Time results: 
        apache.txt = 
        jhu.txt = 0.268 ms/op
        joanne.txt = 0.383 ms/op
        newegg.txt = 11418.243 ms/op
        random164.txt = 
    Open Space results:
        apache.txt = 
        jhu.txt = 23,978,016 bytes
        joanne.txt = 22475688 bytes
        newegg.txt = 55337512 bytes
        random164.txt = 
        