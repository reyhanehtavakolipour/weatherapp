Reyhaneh Tavakolipour

## The trade offs I made and why
I used livedata to emit api response. If I had more time, I would have used kotlin flow instead.
Also I would have spent more time on the UI part(like adding some cool animation).


## More information
My focus to complete this project was architecture and error handling mechanism.

Why Architecture/Structure?
My work experience in different companies and different projects, taught me that any project without having a strong
structure, will eventually face many technical debts.
I learned that whenever the team :
- Start working on new feature without having a robust structure,
- Prioritize coding and just completing a task, rather than building an extendable structure,
  emerging bugs, which are time consuming to fix, is inevitable.


Why Error Handling?
I remember in my previous jobs, sometimes we had a hard time to discover the origin of a specific bug
because we didn't have a generic error handling mechanism to take care of every single scenarios.
Also sometimes managers asked to show a specific error message instead of an actual message from a 3rd library and
again because of lacking a single mechanism, We had some difficulties to find all of them.
