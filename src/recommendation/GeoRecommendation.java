package recommendation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import db.DBConnection;
import db.DBConnectionFactory;
import db.mysql.MySQLConnection;
import entity.Item;

/**
 * Recommendation of events based on geological distance and similar categories.
 */
public class GeoRecommendation {

  public List<Item> recommendItems(String userId, double lat, double lon) {
			List<Item> recommendedItems = new ArrayList<>();

			// 1. Get item IDs of all favorite items
			DBConnection connection = DBConnectionFactory.getConnection();
			Set<String> favoritedItemIds = connection.getFavoriteItemIds(userId);

			// 2. Get all categories of the favorite items, sort by the counts
			Map<String, Integer> allCategories = new HashMap<>();
			for (String itemId : favoritedItemIds) {
				Set<String> categories = connection.getCategories(itemId);
				for (String category : categories) {
					allCategories.put(category, allCategories.getOrDefault(category, 0) + 1);
				}
			}
			List<Entry<String, Integer>> categoryList = new ArrayList<>(allCategories.entrySet());
			Collections.sort(categoryList, (Entry<String, Integer> e1, Entry<String, Integer> e2) -> {
				return Integer.compare(e2.getValue(), e1.getValue());
			});
			
			// 3. Search based on category, filter out items on favorite history
			Set<String> visitedItemIds = new HashSet<>();
			for (Entry<String, Integer> category : categoryList) {
				List<Item> items = connection.searchItems(lat, lon, category.getKey());
				
				for (Item item : items) {
					if (!favoritedItemIds.contains(item.getItemId()) && !visitedItemIds.contains(item.getItemId())) {
						recommendedItems.add(item);
						visitedItemIds.add(item.getItemId());
					}
				}
			}
			
			connection.close();
			return recommendedItems;
  }
}
