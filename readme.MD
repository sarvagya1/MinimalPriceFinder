## Minimal Price Finder

**Description** : Java program to fetch minimal price of baby products user want to purchase corresponding to ShopId.

**File Validation Added**
- Only csv file supported as of now.
- Shop IDs are integers, all products are lower case letters and underscores, and the price is a decimal number.
- Shop id might not present in file in sorted ascending order.

**Product Validation Added**
- User needed product might not be present with any of the shop.
- All user needed products might not present in any single shop.

**Assumption**
- Program currently support 3 arguments i.e, 1 file name and 2 product labels.
- First line of csv file is header.


Sample 1
----------------------------
Data File data.csv <br />
1, 4.00, teddy_bear <br />
2, 5.00, teddy_bear <br />
2, 6.50, baby_powder <br />
3, 5.00, teddy_bear <br />
3, 5.50, baby_powder <br />
4, 7.50, teddy_bear, baby_powder <br />

Program Input
> program data.csv teddy_bear baby_powder

Output
> 4, 7.50
---------------------------

Sample 2
----------------------------
Data File data.csv <br />
3, 4.00, pampers_diapers <br />
3, 8.00, johnson_wipes <br />
4, 5.00, johnson_wipes <br />
4, 2.50, cotton_buds <br />

Program Input
> program data.csv pampers_diapers baby_soap

Output
> none
---------------------------

Sample 3
----------------------------
Data File data.csv <br />
5, 4.00, bath_towel <br />
6, 5.00, scissor <br />
6, 6.00, bath_towel, cotton_balls, powder_puff <br />
5, 8.00, scissor <br />

Program Input
> program data.csv scissor bath_towel

Output
> 6, 11.0
---------------------------

Sample 4
----------------------------
Data File data.csv <br />
1, 4.00, teddy_bear <br />
s1, 8.00, baby_powder <br />
2, 5.00, teddy_bear <br />
2, 6.50, baby_powder <br />

Program Input
> program data.csv teddy_bear baby_powder

Output
> ERROR Invalid csv file format : ShopId is not integer : s1
---------------------------
