import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Problem B. Space Emergency
 * http://code.google.com/codejam/contest/dashboard?c=1128486#s=p1
 * 
 *        Problem:
 * 
 *        There's an emergency—in space! You need to send your fleet's flagship
 *        as quickly as possible from star 0 to star N, traveling through the
 *        other stars in increasing numerical order along the way (0→1→...→N).
 *        Your flagship normally travels at a speed of 0.5 parsecs per hour.
 * 
 *        In addition to sending your flagship, you can order your engineers to
 *        build up to L speed boosters at different stars. Building a speed
 *        booster takes t hours, and all L speed boosters can be built in
 *        parallel. While your flagship travels from a star with a completed
 *        speed booster to the next star, its speed is 1 parsec per hour.
 * 
 *        If a speed booster is completed at a star while your flagship is
 *        traveling from that star to the next one, your flagship will start
 *        moving faster as soon as the speed booster is completed.
 * 
 *        How many hours does it take your flagship to get to star N if you
 *        build speed boosters to make it arrive as soon as possible?
 * 
 *        Input
 * 
 *        The first line of the input gives the number of test cases, T. T lines
 *        follow. Each contains integers, L, t, N and C, followed by C integers
 *        ai, all separated by spaces. ai is the number of parsecs between star
 *        k*C+i and star k*C+i+1, for all integer values of k.
 * 
 *        For example, with N=8, C=3, a0=3, a1=5 and a2=4, the distances between
 *        stars are [3, 5, 4, 3, 5, 4, 3, 5].
 * 
 *        Output
 * 
 *        For each test case, output one line containing "Case #x: y", where x
 *        is the case number (starting from 1) and y is a single integer: the
 *        number of hours it takes to reach star N. The answer is guaranteed to
 *        always be an integer.
 * 
 *        Limits
 * 
 *        1 ≤ T ≤ 100. 
 *        1 ≤ C ≤ 1000. 
 *        C ≤ N. 
 *        1 ≤ ai ≤ 10^4. 
 *        0 ≤ t ≤ 10^11. 
 *        t is even.
 * 
 *        Small dataset
 * 
 *        1 ≤ N ≤ 1000. 
 *        0 ≤ L ≤ 2.
 * 
 *        Large dataset
 * 
 *        1 ≤ N ≤ 10^6. 
 *        0 ≤ L ≤ N.
 * 
 *        Sample:
 * 
 *        Input 
 *        2 
 *        2 20 8 2 3 5 
 *        1 4 2 2 10 4
 * 
 *        Output 
 *        Case #1: 54 
 *        Case #2: 20
 *        
 *        Explanation
 *        In the second case, we can build one speed booster. The distances between 
 *        stars are [10, 4]. We build the speed booster on the first star. After 4
 *        hours, our flagship has gone 2 parsecs and the speed booster is complete.
 *        It takes our flagship another 8 hours to get to star 1, then 8 more hours
 *        to get to star 2, our destination.
 *        
 *        Note: This problem takes place in a universe where the speed of light is 
 *        much higher than 1 parsec per hour, so we don't have to worry about special
 *        relativistic effects.
 * 
 * @author Felipe Ribeiro <felipernb@gmail.com>
 */
public class SpaceEmergency {

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		for (int i = 1; i <= t; i++) {
			int nSpeedBoosters = in.nextInt(); // l
			long timeToBuild = in.nextLong(); // t
			int destination = in.nextInt(); // N
			int c = in.nextInt();
			long[] distancesArray = new long[destination];
			int totalDistance = 0;
			for (int j = 0; j < c; j++) {
				int d = in.nextInt();
				for (int k = 0, p = 0; (p = k * c + j) < destination; k++) {
					distancesArray[p] = d;
					totalDistance += d;
				}
			}
			LinkedList<Long> distances = new LinkedList<Long>();
			for (Long d : distancesArray)
				distances.add(d);
			long distanceWithoutBooster = timeToBuild / 2;
			long totalTime = 0;

			while (distanceWithoutBooster > 0 && !distances.isEmpty()) {
				if (distanceWithoutBooster >= distances.getFirst()) {
					distanceWithoutBooster -= distances.getFirst();
					totalTime += distances.getFirst() * 2;
					distances.removeFirst();
				} else {
					distances.set(0, distances.getFirst()
							- distanceWithoutBooster);
					totalTime += distanceWithoutBooster * 2;
					distanceWithoutBooster = 0;
				}
			}
			Collections.sort(distances);
			while (nSpeedBoosters > 0 && !distances.isEmpty()) {
				totalTime += distances.getLast();
				distances.removeLast();
				nSpeedBoosters--;
			}
			for (Long d : distances) {
				totalTime += d * 2;
			}

			System.out.println("Case #" + i + ": " + totalTime);
		}
	}

}
