# `searcher`
Uses Jaro-Wrinkler distance to find closest result to search term.

Example usage:
```java
String[] entries = new String[]{
        "THE RED COW JUMPED OVER THE GREEN CHICKEN",
        "THE RED COW JUMPED OVER THE RED COW",
        "THE RED FOX JUMPED OVER THE BROWN COW"
};

SearchResult search = Searcher.instance().searchFor("THE BROWN FOX JUMPED OVER THE RED COW", entries);
System.out.println(search);
```
Output:
```
SearchResult{result='THE RED FOX JUMPED OVER THE BROWN COW', similarity=0.885214626391097, term='THE BROWN FOX JUMPED OVER THE RED COW', in=[THE RED COW JUMPED OVER THE GREEN CHICKEN, THE RED COW JUMPED OVER THE RED COW, THE RED FOX JUMPED OVER THE BROWN COW]}
```
