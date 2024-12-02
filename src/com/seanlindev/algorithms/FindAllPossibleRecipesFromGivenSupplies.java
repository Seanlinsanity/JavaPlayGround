package com.seanlindev.algorithms;

import java.util.*;

/*
You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. Ingredients to a recipe may need to be created from other recipes, i.e., ingredients[i] may contain a string that is in recipes.

You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.

Return a list of all the recipes that you can create. You may return the answer in any order.

Note that two recipes may contain each other in their ingredients.



Example 1:

Input: recipes = ["bread"], ingredients = [["yeast","flour"]], supplies = ["yeast","flour","corn"]
Output: ["bread"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
Example 2:

Input: recipes = ["bread","sandwich"], ingredients = [["yeast","flour"],["bread","meat"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
Example 3:

Input: recipes = ["bread","sandwich","burger"], ingredients = [["yeast","flour"],["bread","meat"],["sandwich","meat","bread"]], supplies = ["yeast","flour","meat"]
Output: ["bread","sandwich","burger"]
Explanation:
We can create "bread" since we have the ingredients "yeast" and "flour".
We can create "sandwich" since we have the ingredient "meat" and can create the ingredient "bread".
We can create "burger" since we have the ingredient "meat" and can create the ingredients "bread" and "sandwich".


Constraints:

n == recipes.length == ingredients.length
1 <= n <= 100
1 <= ingredients[i].length, supplies.length <= 100
1 <= recipes[i].length, ingredients[i][j].length, supplies[k].length <= 10
recipes[i], ingredients[i][j], and supplies[k] consist only of lowercase English letters.
All the values of recipes and supplies combined are unique.
Each ingredients[i] does not contain any duplicate values.
 */
public class FindAllPossibleRecipesFromGivenSupplies {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Set<String> recipeSet = new HashSet<>(); //recipeSet=[bread]
        Map<String, List<String>> ingredientsMap = new HashMap<>(); //{bread: ["yeast","flour"]}
        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];
            ingredientsMap.put(recipe, ingredients.get(i));
            recipeSet.add(recipe);
        }

        Set<String> suppliesSet = new HashSet<>(); //suppliesSet=["yeast","flour","corn"]
        Collections.addAll(suppliesSet, supplies);

        List<String> result = new ArrayList<>();//result=[]
        Map<String, Set<String>> visit = new HashMap<>();//visit={}
        for (String recipe: recipes) {
            Set<String> path = dfs(recipe, new HashSet<>(), visit, ingredientsMap);
            if (path == null) { continue; }
            boolean isValid = true;
            for (String item: path) {
                if (!recipeSet.contains(item) && !suppliesSet.contains(item)) {
                    isValid = false;
                    break;
                }
            }

            if (isValid) { result.add(recipe); }
        }

        return result;
    }

    Set<String> dfs(String node,  Set<String> cycles, Map<String, Set<String>> visit, Map<String, List<String>> ingredientsMap) {
        if (cycles.contains(node)) { return null; }
        if (visit.get(node) != null) { return visit.get(node); }

        cycles.add(node); //cycles={bread}
        Set<String> path = new HashSet<>();
        path.add(node); //path=[flour]
        List<String> ingredChilds = ingredientsMap.get(node); //ingredChilds=[yeast,flour]
        if (ingredChilds != null) {
            for (String ingred: ingredChilds) {
                Set<String> subPath = dfs(ingred, cycles, visit, ingredientsMap);
                if (subPath == null) { return null; }

                path.addAll(subPath);
            }
        }
        cycles.remove(node);
        visit.put(node, path);
        return path;
    }
}
