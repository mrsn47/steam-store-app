/* 
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */
import com.example.steam_store_app.core.service.model.Platforms
import com.google.gson.annotations.SerializedName


data class Data (

	@SerializedName("type") val type : String,
	@SerializedName("name") val name : String,
	@SerializedName("steam_appid") val steam_appid : Int,
	@SerializedName("required_age") val required_age : Int,
	@SerializedName("is_free") val is_free : Boolean,
	@SerializedName("dlc") val dlc : List<Int>,
	@SerializedName("detailed_description") val detailed_description : String,
	@SerializedName("about_the_game") val about_the_game : String,
	@SerializedName("short_description") val short_description : String,
	@SerializedName("supported_languages") val supported_languages : String,
	@SerializedName("header_image") val header_image : String,
	@SerializedName("website") val website : String,
	@SerializedName("developers") val developers : List<String>,
	@SerializedName("publishers") val publishers : List<String>,
	@SerializedName("packages") val packages : List<Int>,
	@SerializedName("package_groups") val package_groups : List<Package_groups>,
	@SerializedName("platforms") val platforms : Platforms,
	@SerializedName("metacritic") val metacritic : Metacritic,
	@SerializedName("categories") val categories : List<Categories>,
	@SerializedName("genres") val genres : List<Genres>,
	@SerializedName("screenshots") val screenshots : List<Screenshots>,
	@SerializedName("movies") val movies : List<Movies>,
	@SerializedName("recommendations") val recommendations : Recommendations,
	@SerializedName("achievements") val achievements : Achievements,
	@SerializedName("release_date") val release_date : Release_date,
	@SerializedName("support_info") val support_info : Support_info,
	@SerializedName("background") val background : String,
	@SerializedName("content_descriptors") val content_descriptors : Content_descriptors
)