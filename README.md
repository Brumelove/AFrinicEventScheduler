# AFrinicEventScheduler
Successful interview anser for Senior Software Engineer Afrinic
Dear candidate,

AFRINIC holds two meetings per year. One in May, another one in
November. See https://meeting.afrinic.net/ for more information.

During these meetings, people from our community are invited to talk
and do presentations. We have also many mandatory events like the
members meetings (AGMM), resource policy discussions (RPD) and many
others.

Our Head of Communications and PR has the responsibility to organize
these meetings and find the best way to arrange the proposed talks,
presentations, members events, etc. You will see an example of a meeting
programme for our recent November Meeting in Tunisia here:
https://meeting.afrinic.net/afrinic-29/agenda/programme

For this exercise, you will help our Head of Communications organize
such a meeting and you will write a program that will take several
events and arrange them in a way that suits the following constraints:

1. The meeting spans over multiple days, each of which has a morning
and an afternoon session.

2. Each session contains multiple events.

3. A morning session begins at 9am and finishes by 12.30pm.  

4. There is a mandatory lunch time, between 12.30pm and 2.00pm.

5. An afternoon session begins at 2.00pm and must finish in time for the
daily networking event.

6. The networking event must start between 4:30pm and 5:30pm. It has no
predefined duration, it can last as long as there are participants.

7. The events/presensations titles do not have numbers.
  
8. Events/presentations lengths are in minutes, except for the
PechaKucha (https://en.wikipedia.org/wiki/PechaKucha), that last 5min.

9. We will also assume that everyone is on time and that there is no
time lost between two consecutive sessions.

10. A smart implementation would allow cost-savings as each additional
day has a huge cost.

Your mission is to write a program that will automatically take a list
of events as input (CSV file) and output a proposed meeting programme.
Here is an example of an input file:

Deployathon;90min;David
FIRE Workshop;90min;Olatunde
Connecting the Unconnected in Africa;30min;Mukom
Internet Governance;60min;Patricia
IoT impact on Internet Governance Processes;PechaKucha;Alain
Building registry ATI experience;45min;Wafa
Annual General Members Meeting;180min;Eddy
Database Working Group Session;120min;Simon & Michel
Regional TTLDs;30min;Daheda
Benefits of THD networks;45min;Amreesh
Tech talk: Raiders of the lost ark;PechaKucha;Jordi

And a possible output would be:

Day 1:

Morning session:
09:00-10:30: Deployathlon (David)
10:30-12:30: Database Working Group Session (Simon & Michel)

12:30-02:00: Lunch

Afternoon session:
02:00-03:00: Internet Governance (Patricia)
03:00-03:05: Tech talk: Raiders of the lost ark (Jordi)
03:05-03:50: Building registry ATI experience (Wafa)
03:50-05:20: FIRE Workshop (Olatunde)
05:20: Networking

Day 2:

Morning session:
09:00-12:00: Annual General Members Meeting (Eddy)
12:00-12:30: Connecting the Unconnected in Africa (Mukom)

12:30-02:00: Lunch

Afternoon session:
02:00-02:45: Benefits of THD networks (Amreesh)
02:45-02:50: IoT impact on Internet Governance Processes (Alain)
02:50-03:20: Regional TTLDs (Daheda)
04:30: Networking

Note that you do NOT need to replicate exactly the output given above.
Depending on your implementation and algorithms, your output may vary.
This is acceptable.

Your code will be analyzed and we will be particularly careful about:

1. Your code structure and architecture.
2. The complexity of your algorithms.
3. The unit tests code coverage.
4. The error handling.
5. The overall code quality.

Your code must be compilable and executable on Linux. The deliverables
are:

1. Your source code.
2. All the instructions to compile it and run it.

You will also be invited to explain and justify your technical choices
in a future interview.

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/b251da152c334bf695eba5af1bc914d8)](https://www.codacy.com/gh/Brumelove/AFrinicEventScheduler/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Brumelove/AFrinicEventScheduler&amp;utm_campaign=Badge_Grade)
