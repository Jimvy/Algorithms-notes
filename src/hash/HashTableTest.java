package hash;

import org.junit.Test;
import search.SearchTable;

import java.text.DecimalFormat;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

public class HashTableTest {
    @Test
    public void testLinearProbing() {
        SearchTable<String, Integer> searchTable = new LinearProbingHashTable<>(97);
        //testRandomInsert(searchTable);
        //test1(searchTable);
        //firstTest(new LinearProbingHashTable<>(53));
        secondTest(new LinearProbingHashTable<>(53));
        fourthTest(new LinearProbingHashTable<>(53));
        SearchTable<String, Long> searchTable1 = new LinearProbingHashTable<>();
        sixthTest(searchTable1);
    }
    @Test
    public void testSeparateChainingHashTable() {
        SearchTable<String, Integer> st1 = new SeparateChainingHashTable<>();
        SearchTable<String, Long> st2 = new SeparateChainingHashTable<>();
        testRandomInsert(st1);
        test1(st1);
        firstTest(new SeparateChainingHashTable<>());
        secondTest(new SeparateChainingHashTable<>(97));
        fourthTest(new SeparateChainingHashTable<>(97));
        sixthTest(st2);
    }

    public void testRandomInsert(SearchTable<String, Integer> searchTable) {
        //
    }
    public void test1(SearchTable<String, Integer> searchTable) {
        String[] strings = new String[] {" salut", "ça va ?", "coucou", "lol", "hello", "quarante-deux"};
        int i = 0;
        for (String s : strings) {
            searchTable.put(s, i++);
            assertTrue(searchTable.contains(s));
        }
        i = 0;
        for (String s : strings) {
            assertEquals(i++, (int) searchTable.get(s));
        }
    }

    // Esting code from mission 4
    private void firstTest(SearchTable<String, Integer> map) {
        map.put("a", 1);
        assertEquals(map.get("a"), new Integer(1));
    }

    private void secondTest(SearchTable<String, Integer> map) {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        map.put("ten", 10);
        map.put("eleven", 11);
        map.put("twelve", 12);
        map.put("thirteen", 13);
        map.put("fourteen", 14);
        map.put("fifteen", 15);
        map.put("sixteen", 16);
        map.put("seventeen", 17);
        map.put("eightteen", 18);
        map.put("nineteen", 19);
        map.put("twenty", 20);
        assertEquals(map.size(), 20);
        assertEquals(map.get("one"), new Integer(1));
        assertEquals(map.get("two"), new Integer(2));
        assertEquals(map.get("three"), new Integer(3));
        assertEquals(map.get("four"), new Integer(4));
        assertEquals(map.get("five"), new Integer(5));
        assertEquals(map.get("six"), new Integer(6));
        assertEquals(map.get("seven"), new Integer(7));
        assertEquals(map.get("eight"), new Integer(8));
        assertEquals(map.get("nine"), new Integer(9));
        assertEquals(map.get("ten"), new Integer(10));
        assertEquals(map.get("eleven"), new Integer(11));
        assertEquals(map.get("twelve"), new Integer(12));
        assertEquals(map.get("thirteen"), new Integer(13));
        assertEquals(map.get("fourteen"), new Integer(14));
        assertEquals(map.get("fifteen"), new Integer(15));
        assertEquals(map.get("sixteen"), new Integer(16));
        assertEquals(map.get("seventeen"), new Integer(17));
        assertEquals(map.get("eightteen"), new Integer(18));
        assertEquals(map.get("nineteen"), new Integer(19));
        assertEquals(map.get("twenty"), new Integer(20));
        assertEquals(map.get("zero"), null);
        assertEquals(map.size(), 20);
        map.put("one", null);
        map.put("two", null);
        map.put("three", null);
        map.put("four", null);
        map.put("five", null);
        map.put("six", null);
        map.put("seven", null);
        map.put("eight", null);
        map.put("nine", null);
        map.put("ten", null);
        map.put("eleven", null);
        map.put("twelve", null);
        map.put("thirteen", null);
        map.put("fourteen", null);
        map.put("fifteen", null);
        map.put("sixteen", null);
        map.put("seventeen", null);
        map.put("eightteen", null);
        map.put("nineteen", null);
        map.put("twenty", null);
        assertEquals(0, map.size() );
    }
    private void fourthTest(SearchTable<String, Integer> map) {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        map.put("ten", 10);
        map.put("eleven", 11);
        map.put("twelve", 12);
        map.put("thirteen", 13);
        map.put("fourteen", 14);
        map.put("fifteen", 15);
        map.put("sixteen", 16);
        map.put("seventeen", 17);
        map.put("eightteen", 18);
        map.put("nineteen", 19);
        map.put("twenty", 20);
        assertEquals(map.get("six"), map.get("six"));
        assertNull(map.get("zero"));
        map.put("one", null);
        map.remove("two");
        assertTrue(map.delete("three", 3));
        assertFalse(map.delete("four", 5));
        assertNull(map.get("zero"));
        assertNull(map.get("one"));
        assertNull(map.get("two"));
        assertFalse(map.contains("three"));
        assertNull(map.get("four"));
		/*} catch (IOException e) {
			fail();
		}*/
        //assertNull(map.get(null));
    }
    private void sixthTest(SearchTable<String, Long> map) {
        for (long i = 0; i < 2048; i++) {
            map.put(convert(i), i);
        }
        for (long i = 0; i < 2048; i++) {
            assertEquals(new Long(i), map.get(convert(i)));
        }
    }
    // source : https://stackoverflow.com/questions/3911966/how-to-convert-number-to-words-in-java
    // (flemme de le faire moi-même)
    private static final String[] tensNames = {
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
    };

    private static final String[] numNames = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
    };
    private static String convertLessThanOneThousand(int number) {
        String soFar;

        if (number % 100 < 20){
            soFar = numNames[number % 100];
            number /= 100;
        }
        else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;
        }
        if (number == 0) return soFar;
        return numNames[number] + " hundred" + soFar;
    }
    private static String convert(long number) {
        // 0 to 999 999 999 999
        if (number == 0) { return "zero"; }

        String snumber;

        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(snumber.substring(0,3));
        // nnnXXXnnnnnn
        int millions  = Integer.parseInt(snumber.substring(3,6));
        // nnnnnnXXXnnn
        int hundredThousands = Integer.parseInt(snumber.substring(6,9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(snumber.substring(9,12));

        String tradBillions;
        switch (billions) {
            case 0:
                tradBillions = "";
                break;
            case 1 :
                tradBillions = convertLessThanOneThousand(billions)
                        + " billion ";
                break;
            default :
                tradBillions = convertLessThanOneThousand(billions)
                        + " billion ";
        }
        String result =  tradBillions;

        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            case 1 :
                tradMillions = convertLessThanOneThousand(millions)
                        + " million ";
                break;
            default :
                tradMillions = convertLessThanOneThousand(millions)
                        + " million ";
        }
        result =  result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1 :
                tradHundredThousands = "one thousand ";
                break;
            default :
                tradHundredThousands = convertLessThanOneThousand(hundredThousands)
                        + " thousand ";
        }
        result =  result + tradHundredThousands;

        String tradThousand;
        tradThousand = convertLessThanOneThousand(thousands);
        result =  result + tradThousand;

        // remove extra spaces!
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }

    // Testing code from mission 3
    /*
    @Test
    public void firstTest() {
        try {
            SearchTable tree = new LinearProbingHashTable();
            String key = "Foo Fighters";
            Set<String> value = new HashSet<String>();
            value.add("The Pretender");
            assertEquals(tree.put(key, value), null);
            assertEquals(tree.size(), 1);
            assertEquals(tree.isEmpty(), false);
            Set<String> result = tree.get(key);
            assertEquals(result.size(), 1);
            assertTrue(result.contains("The Pretender"));
        } catch (Exception e) {
            fail("Exception occured : " + e);
        }
    }

    @Test
    public void constructorTest() {
        try {
            SearchTable tree = new LinearProbingHashTable("songs.txt");
            assertNotNull(tree);
            String key = "Aerosmith";
            Set<String> result = tree.get(key);
            assertTrue(result.contains("Dream On"));
            assertTrue(!result.contains("LOL"));
        } catch (Exception e) {
            fail("Exception occured : " + e);
        }
    }

    @Test
    public void removeTest() {
        try {
            SearchTable tree = new LinearProbingHashTable();
            String key = "Foo Fighters";
            Set<String> value1 = new HashSet<String>();
            Set<String> value2 = new HashSet<String>();
            value1.add("The Pretender");
            assertEquals(tree.isEmpty(), true);
            assertEquals(tree.put(key, value1), null);
            assertEquals(tree.size(), 1);
            assertEquals(tree.isEmpty(), false);
            key = "Guns n' Roses";
            value2.add("November Rain");
            assertEquals(tree.put(key, value2), null);
            assertEquals(tree.size(), 2);
            Set<String> result = tree.remove("Foo Fighters");
            assertEquals(result.size(), 1);
            assertTrue(result.contains("The Pretender"));
            assertEquals(tree.size(), 1);
            result = tree.remove("Guns n' Roses");
            assertEquals(result.size(), 1);
            assertTrue(result.contains("November Rain"));
            assertEquals(tree.size(), 0);
            assertEquals(tree.isEmpty(), true);
        } catch (Exception e) {
            fail("Exception occured : " + e);
        }
    }

    @Test
    public void valuesTest() {
        try {
            SearchTable tree = new LinearProbingHashTable();
            String key = "Foo Fighters";
            Set<String> value1 = new HashSet<String>();
            Set<String> value2 = new HashSet<String>();
            value1.add("The Pretender");
            assertEquals(tree.isEmpty(), true);
            assertEquals(tree.put(key, value1), null);
            assertEquals(tree.size(), 1);
            assertEquals(tree.isEmpty(), false);
            key = "Guns n' Roses";
            value2.add("November Rain");
            assertEquals(tree.put(key, value2), null);
            assertEquals(tree.size(), 2);
            Collection<Set<String>> values = tree.values();
            assertTrue(values.contains(value1));
            assertTrue(values.contains(value2));
        } catch (Exception e) {
            fail("Exception occured : " + e);
        }
    }

    @Test
    public void putTest() {
        try {
            SearchTable tree = new LinearProbingHashTable();
            String key = "Foo Fighters";
            Set<String> value1 = new HashSet<String>();
            Set<String> value2 = new HashSet<String>();
            value1.add("The Pretender");
            value2.add("Best of You");
            assertEquals(tree.isEmpty(), true);
            assertEquals(tree.put(key, value1), null);
            assertEquals(tree.size(), 1);
            assertEquals(tree.isEmpty(), false);
            assertTrue(tree.put(key, value2).equals(value1));
        } catch (Exception e) {
            fail("Exception occured : " + e);
        }
    }

    @Test
    public void getOrderedTest() {
        try {
            SearchTable tree = new LinearProbingHashTable();
            String[] result;
            result = tree.getOrdered("LOL");
            assertEquals(result.length, 0);

            String key = "Foo Fighters";
            Set<String> value = new HashSet<String>();
            Set<String> value2 = new HashSet<String>();
            value.add("The Pretender");
            value.add("Everlong");
            value.add("Best of You");
            assertEquals(tree.put(key, value), null);
            result = tree.getOrdered(key);
            String[] expectedResult = {"Best of You", "Everlong", "The Pretender"};
            assertTrue(Arrays.equals(result, expectedResult));

            value2.add("Money");
            value2.add("Another Brick in The Wall");
            value2.add("Have a Cigar");
            assertEquals(tree.put("Pink Floyd", value2), null);
            result = tree.getOrdered("Pink Floyd");
            String[] expectedResult2 = {"Another Brick in The Wall", "Have a Cigar", "Money"};
            assertTrue(Arrays.equals(result, expectedResult2));

            result = tree.getOrdered("LOL");
            assertEquals(result.length, 0);
        } catch (Exception e) {
            fail("Exception occured : " + e);
        }
    }

    @Test
    public void toStringTest() {
        try {
            SearchTable tree = new LinearProbingHashTable();
            String key = "Foo Fighters";
            Set<String> value = new HashSet<String>();
            value.add("The Pretender");
            value.add("Everlong");
            value.add("Best of You");
            assertEquals(tree.put(key, value), null);
            String result = tree.toString();
            assertEquals(result, "[Foo Fighters] Best of You\n"
                    + "[Foo Fighters] Everlong\n"
                    + "[Foo Fighters] The Pretender\n");
        } catch (Exception e) {
            fail("Exception occured : " + e);
        }
    }

    @Test
    public void entriesBetweenTest() {
        try {
            SearchTable tree = new LinearProbingHashTable();

            List<Map.Entry<String, Set<String>>> resultList;
            resultList = tree.entriesBetween("K", "T");
            assertEquals(resultList.isEmpty(), true);

            String key = "Foo Fighters";
            Set<String> value = new HashSet<String>();
            Set<String> value2 = new HashSet<String>();
            Set<String> value3 = new HashSet<String>();
            Set<String> value4 = new HashSet<String>();
            value.add("The Pretender");
            value.add("Everlong");
            value.add("Best of You");
            assertEquals(tree.put(key, value), null);

            key = "Nirvana";
            value2.add("Smells like Teen Spirit");
            assertEquals(tree.put(key, value2), null);
            key = "Guns n' Roses";
            value3.add("November Rain");
            assertEquals(tree.put(key, value3), null);
            key = "Pink Floyd";
            value4.add("Money");
            assertEquals(tree.put(key, value4), null);

            resultList = new LinkedList<Map.Entry<String, Set<String>>>();
            resultList = tree.entriesBetween("Foo Fighters", "Foo Fighters");
            assertEquals(resultList.size(), 1);

            resultList = tree.entriesBetween("Foo Fighters", "Pink Floyd");
            assertEquals(resultList.size(), 4);

            resultList = tree.entriesBetween("Foo Fighters", "Guns n' Roses");
            assertEquals(resultList.size(), 2);

            resultList = tree.entriesBetween("K", "P");
            assertEquals(resultList.size(), 1);

            resultList = tree.entriesBetween("Foo Fighters", "P");
            assertEquals(resultList.size(), 3);

            resultList = tree.entriesBetween("A", "C");
            assertEquals(resultList.size(), 0);

            resultList = tree.entriesBetween("A", "Pink Floyd");
            assertEquals(resultList.size(), 4);

            resultList = tree.entriesBetween("Foo Fighters", "X");
            assertEquals(resultList.size(), 4);

            resultList = tree.entriesBetween("A", "X");
            assertEquals(resultList.size(), 4);
        } catch (Exception e) {
            fail("Exception occured : " + e);
        }
    }

    @Test
    public void firstEntryTest() {
        try {
            SearchTable tree = new LinearProbingHashTable();

            Map.Entry<String, Set<String>> result;
            result = tree.firstEntry();
            assertEquals(result, null);

            String key = "Foo Fighters";
            Set<String> value1 = new HashSet<String>();
            Set<String> value2 = new HashSet<String>();
            Set<String> value3 = new HashSet<String>();
            Set<String> value4 = new HashSet<String>();
            value1.add("The Pretender");
            value1.add("Everlong");
            value1.add("Best of You");
            assertEquals(tree.put(key, value1), null);
            result = tree.firstEntry();
            String entryKey = result.getKey();
            assertEquals(entryKey, key);

            key = "Nirvana";
            value2.add("Smells like Teen Spirit");
            assertEquals(tree.put(key, value2), null);
            key = "Guns n' Roses";
            value3.add("November Rain");
            assertEquals(tree.put(key, value3), null);
            key = "Pink Floyd";
            value4.add("Money");
            assertEquals(tree.put(key, value4), null);
            result = tree.firstEntry();
            entryKey = result.getKey();
            assertEquals(entryKey, "Foo Fighters");
        } catch (Exception e) {
            fail("Exception occured : " + e);
        }
    }

    @Test
    public void lastEntryTest() {
        try {
            SearchTable tree = new LinearProbingHashTable();

            Map.Entry<String, Set<String>> result;
            result = tree.lastEntry();
            assertEquals(result, null);

            String key = "Foo Fighters";
            Set<String> value1 = new HashSet<String>();
            Set<String> value2 = new HashSet<String>();
            Set<String> value3 = new HashSet<String>();
            Set<String> value4 = new HashSet<String>();
            value1.add("The Pretender");
            value1.add("Everlong");
            value1.add("Best of You");
            assertEquals(tree.put(key, value1), null);
            result = tree.lastEntry();
            String entryKey = result.getKey();
            assertEquals(entryKey, key);

            key = "Nirvana";
            value2.add("Smells like Teen Spirit");
            assertEquals(tree.put(key, value2), null);
            key = "Guns n' Roses";
            value3.add("November Rain");
            assertEquals(tree.put(key, value3), null);
            key = "Pink Floyd";
            value4.add("Money");
            assertEquals(tree.put(key, value4), null);
            result = tree.lastEntry();
            entryKey = result.getKey();
            assertEquals(entryKey, "Pink Floyd");
        } catch (Exception e) {
            fail("Exception occured : " + e);
        }
    }

    @Test
    public void ceilingEntryTest() {
        try {
            SearchTable tree = new LinearProbingHashTable();

            Map.Entry<String, Set<String>> result;
            result = tree.ceilingEntry("K");
            assertEquals(result, null);

            String key = "Foo Fighters";
            Set<String> value = new HashSet<String>();
            Set<String> value2 = new HashSet<String>();
            Set<String> value3 = new HashSet<String>();
            Set<String> value4 = new HashSet<String>();
            value.add("The Pretender");
            assertEquals(tree.put(key, value), null);
            key = "Nirvana";
            value2.add("Smells like Teen Spirit");
            assertEquals(tree.put(key, value2), null);
            key = "Guns n' Roses";
            value3.add("November Rain");
            assertEquals(tree.put(key, value3), null);
            key = "Pink Floyd";
            value4.add("Money");
            assertEquals(tree.put(key, value4), null);

            result = tree.ceilingEntry("Pink Floyd");
            String entryKey = result.getKey();
            assertEquals(entryKey, "Pink Floyd");

            result = tree.ceilingEntry("R");
            assertEquals(result, null);

            result = tree.ceilingEntry("Guns n' Roses");
            entryKey = result.getKey();
            assertEquals(entryKey, "Guns n' Roses");

            result = tree.ceilingEntry("K");
            entryKey = result.getKey();
            assertEquals(entryKey, "Nirvana");

            result = tree.ceilingEntry("A");
            entryKey = result.getKey();
            assertEquals(entryKey, "Foo Fighters");
        } catch (Exception e) {
            fail("Exception occured : " + e);
        }
    }

    @Test
    public void floorEntryTest() {
        try {
            SearchTable tree = new LinearProbingHashTable();

            Map.Entry<String, Set<String>> result;
            result = tree.floorEntry("K");
            assertEquals(result, null);

            String key = "Foo Fighters";
            Set<String> value = new HashSet<String>();
            Set<String> value2 = new HashSet<String>();
            Set<String> value3 = new HashSet<String>();
            Set<String> value4 = new HashSet<String>();
            value.add("The Pretender");
            assertEquals(tree.put(key, value), null);
            key = "Nirvana";
            value2.add("Smells like Teen Spirit");
            assertEquals(tree.put(key, value2), null);
            key = "Guns n' Roses";
            value3.add("November Rain");
            assertEquals(tree.put(key, value3), null);
            key = "Pink Floyd";
            value4.add("Money");
            assertEquals(tree.put(key, value4), null);

            result = tree.floorEntry("Nirvana");
            String entryKey = result.getKey();
            assertEquals(entryKey, "Nirvana");

            result = tree.floorEntry("R");
            entryKey = result.getKey();
            assertEquals(entryKey, "Pink Floyd");

            result = tree.floorEntry("Guns n' Roses");
            entryKey = result.getKey();
            assertEquals(entryKey, "Guns n' Roses");

            result = tree.floorEntry("K");
            entryKey = result.getKey();
            assertEquals(entryKey, "Guns n' Roses");

            result = tree.floorEntry("A");
            assertEquals(result, null);
        } catch (Exception e) {
            fail("Exception occured : " + e);
        }
    }

    @Test
    public void lowerEntryTest() {
        try {
            SearchTable tree = new LinearProbingHashTable();

            Map.Entry<String, Set<String>> result;
            result = tree.lowerEntry("K");
            assertEquals(result, null);

            String key = "Foo Fighters";
            Set<String> value = new HashSet<String>();
            Set<String> value2 = new HashSet<String>();
            Set<String> value3 = new HashSet<String>();
            Set<String> value4 = new HashSet<String>();
            value.add("The Pretender");
            assertEquals(tree.put(key, value), null);
            key = "Nirvana";
            value2.add("Smells like Teen Spirit");
            assertEquals(tree.put(key, value2), null);
            key = "Guns n' Roses";
            value3.add("November Rain");
            assertEquals(tree.put(key, value3), null);
            key = "Pink Floyd";
            value4.add("Money");
            assertEquals(tree.put(key, value4), null);

            result = tree.lowerEntry("Foo Fighters");
            assertEquals(result, null);
            String entryKey;

            result = tree.lowerEntry("R");
            entryKey = result.getKey();
            assertEquals(entryKey, "Pink Floyd");

            result = tree.lowerEntry("Guns n' Roses");
            entryKey = result.getKey();
            assertEquals(entryKey, "Foo Fighters");

            result = tree.lowerEntry("K");
            entryKey = result.getKey();
            assertEquals(entryKey, "Guns n' Roses");

            result = tree.lowerEntry("A");
            assertEquals(result, null);
        } catch (Exception e) {
            fail("Exception occured : " + e);
        }
    }

    @Test
    public void higherEntryTest() {
        try {
            SearchTable tree = new LinearProbingHashTable();

            Map.Entry<String, Set<String>> result;
            result = tree.higherEntry("K");
            assertEquals(result, null);

            String key = "Foo Fighters";
            Set<String> value = new HashSet<String>();
            Set<String> value2 = new HashSet<String>();
            Set<String> value3 = new HashSet<String>();
            Set<String> value4 = new HashSet<String>();
            value.add("The Pretender");
            assertEquals(tree.put(key, value), null);
            key = "Nirvana";
            value2.add("Smells like Teen Spirit");
            assertEquals(tree.put(key, value2), null);
            key = "Guns n' Roses";
            value3.add("November Rain");
            assertEquals(tree.put(key, value3), null);
            key = "Pink Floyd";
            value4.add("Money");
            assertEquals(tree.put(key, value4), null);

            result = tree.higherEntry("Pink Floyd");
            assertEquals(result, null);

            String entryKey;
            result = tree.higherEntry("R");
            assertEquals(result, null);

            result = tree.higherEntry("Guns n' Roses");
            entryKey = result.getKey();
            assertEquals(entryKey, "Nirvana");

            result = tree.higherEntry("K");
            entryKey = result.getKey();
            assertEquals(entryKey, "Nirvana");

            result = tree.higherEntry("A");
            entryKey = result.getKey();
            assertEquals(entryKey, "Foo Fighters");
        } catch (Exception e) {
            fail("Exception occured : " + e);
        }
    }
    */
}
