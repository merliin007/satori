/*
 * Created by Miguel Angel Aguilar Cuevas
 * 31/08/2018 at 3:19 PM
 */
package utility;

import java.util.List;

public interface CaptainsRetriever <T>{
   List<String> findCaptains(List<T> t);
}
