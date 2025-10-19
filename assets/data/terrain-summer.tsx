<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.11.0" name="terrain-summer" tilewidth="32" tileheight="32" tilecount="4096" columns="64">
 <image source="terrain-summer.png" width="2048" height="2048"/>
 <tile id="1088">
  <animation>
   <frame tileid="1088" duration="300"/>
   <frame tileid="1091" duration="300"/>
   <frame tileid="2112" duration="300"/>
   <frame tileid="2624" duration="300"/>
  </animation>
 </tile>
 <tile id="1089">
  <animation>
   <frame tileid="1089" duration="300"/>
   <frame tileid="1092" duration="300"/>
   <frame tileid="2113" duration="300"/>
   <frame tileid="2625" duration="300"/>
  </animation>
 </tile>
 <tile id="1090">
  <animation>
   <frame tileid="1090" duration="300"/>
   <frame tileid="1093" duration="300"/>
   <frame tileid="2114" duration="300"/>
   <frame tileid="2626" duration="300"/>
  </animation>
 </tile>
 <tile id="1152">
  <animation>
   <frame tileid="1152" duration="300"/>
   <frame tileid="1155" duration="300"/>
   <frame tileid="2176" duration="300"/>
   <frame tileid="2688" duration="300"/>
  </animation>
 </tile>
 <tile id="1154">
  <animation>
   <frame tileid="1154" duration="300"/>
   <frame tileid="1157" duration="300"/>
   <frame tileid="2178" duration="300"/>
   <frame tileid="2690" duration="300"/>
  </animation>
 </tile>
 <tile id="1216">
  <animation>
   <frame tileid="1216" duration="300"/>
   <frame tileid="1219" duration="300"/>
   <frame tileid="2240" duration="300"/>
   <frame tileid="2752" duration="300"/>
  </animation>
 </tile>
 <tile id="1217">
  <animation>
   <frame tileid="1217" duration="300"/>
   <frame tileid="1220" duration="300"/>
   <frame tileid="2241" duration="300"/>
   <frame tileid="2753" duration="300"/>
  </animation>
 </tile>
 <tile id="1218">
  <animation>
   <frame tileid="1218" duration="300"/>
   <frame tileid="1221" duration="300"/>
   <frame tileid="2242" duration="300"/>
   <frame tileid="2754" duration="300"/>
  </animation>
 </tile>
 <tile id="1280">
  <animation>
   <frame tileid="2304" duration="300"/>
   <frame tileid="2312" duration="300"/>
   <frame tileid="1280" duration="300"/>
   <frame tileid="1282" duration="300"/>
  </animation>
 </tile>
 <tile id="1281">
  <animation>
   <frame tileid="1281" duration="300"/>
   <frame tileid="1283" duration="300"/>
   <frame tileid="2305" duration="300"/>
   <frame tileid="2313" duration="300"/>
  </animation>
 </tile>
 <tile id="1344">
  <animation>
   <frame tileid="1344" duration="300"/>
   <frame tileid="1346" duration="300"/>
   <frame tileid="2368" duration="300"/>
   <frame tileid="2376" duration="300"/>
  </animation>
 </tile>
 <tile id="1345">
  <animation>
   <frame tileid="1345" duration="300"/>
   <frame tileid="1347" duration="300"/>
   <frame tileid="2369" duration="300"/>
   <frame tileid="2377" duration="300"/>
  </animation>
 </tile>
 <tile id="1363">
  <animation>
   <frame tileid="89" duration="300"/>
  </animation>
 </tile>
 <wangsets>
  <wangset name="river" type="corner" tile="1280">
   <wangcolor name="glass_river" color="#ff0000" tile="1152" probability="1"/>
   <wangtile tileid="1088" wangid="0,0,0,1,0,0,0,0"/>
   <wangtile tileid="1089" wangid="0,0,0,1,0,1,0,0"/>
   <wangtile tileid="1090" wangid="0,0,0,0,0,1,0,0"/>
   <wangtile tileid="1152" wangid="0,1,0,1,0,0,0,0"/>
   <wangtile tileid="1153" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="1154" wangid="0,0,0,0,0,1,0,1"/>
   <wangtile tileid="1216" wangid="0,1,0,0,0,0,0,0"/>
   <wangtile tileid="1217" wangid="0,1,0,0,0,0,0,1"/>
   <wangtile tileid="1218" wangid="0,0,0,0,0,0,0,1"/>
   <wangtile tileid="1280" wangid="0,1,0,0,0,1,0,1"/>
   <wangtile tileid="1281" wangid="0,1,0,1,0,0,0,1"/>
   <wangtile tileid="1344" wangid="0,0,0,1,0,1,0,1"/>
   <wangtile tileid="1345" wangid="0,1,0,1,0,1,0,0"/>
  </wangset>
  <wangset name="glass" type="corner" tile="0">
   <wangcolor name="glass1" color="#ff0000" tile="384" probability="1"/>
   <wangcolor name="glass3OnSand" color="#0000ff" tile="388" probability="1"/>
   <wangcolor name="glassOnsoil" color="#00aa00" tile="-1" probability="1"/>
   <wangcolor name="SandOnDirt" color="#ff7700" tile="73" probability="1"/>
   <wangtile tileid="0" wangid="0,0,0,1,0,0,0,0"/>
   <wangtile tileid="1" wangid="0,0,0,1,0,1,0,0"/>
   <wangtile tileid="2" wangid="0,0,0,0,0,1,0,0"/>
   <wangtile tileid="6" wangid="0,0,0,3,0,0,0,0"/>
   <wangtile tileid="7" wangid="0,0,0,3,0,3,0,0"/>
   <wangtile tileid="8" wangid="0,0,0,0,0,3,0,0"/>
   <wangtile tileid="9" wangid="0,0,0,4,0,0,0,0"/>
   <wangtile tileid="10" wangid="0,0,0,4,0,4,0,0"/>
   <wangtile tileid="11" wangid="0,0,0,0,0,4,0,0"/>
   <wangtile tileid="12" wangid="0,4,0,0,0,4,0,4"/>
   <wangtile tileid="13" wangid="0,4,0,4,0,0,0,4"/>
   <wangtile tileid="64" wangid="0,1,0,1,0,0,0,0"/>
   <wangtile tileid="65" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="66" wangid="0,0,0,0,0,1,0,1"/>
   <wangtile tileid="70" wangid="0,3,0,3,0,0,0,0"/>
   <wangtile tileid="72" wangid="0,0,0,0,0,3,0,3"/>
   <wangtile tileid="73" wangid="0,4,0,4,0,0,0,0"/>
   <wangtile tileid="74" wangid="0,4,0,4,0,4,0,4"/>
   <wangtile tileid="75" wangid="0,0,0,0,0,4,0,4"/>
   <wangtile tileid="76" wangid="0,0,0,4,0,4,0,4"/>
   <wangtile tileid="77" wangid="0,4,0,4,0,4,0,0"/>
   <wangtile tileid="128" wangid="0,1,0,0,0,0,0,0"/>
   <wangtile tileid="129" wangid="0,1,0,0,0,0,0,1"/>
   <wangtile tileid="130" wangid="0,0,0,0,0,0,0,1"/>
   <wangtile tileid="131" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="132" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="133" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="134" wangid="0,3,0,0,0,0,0,0"/>
   <wangtile tileid="135" wangid="0,3,0,0,0,0,0,3"/>
   <wangtile tileid="136" wangid="0,0,0,0,0,0,0,3"/>
   <wangtile tileid="137" wangid="0,4,0,0,0,0,0,0"/>
   <wangtile tileid="138" wangid="0,4,0,0,0,0,0,4"/>
   <wangtile tileid="139" wangid="0,0,0,0,0,0,0,4"/>
   <wangtile tileid="198" wangid="0,3,0,0,0,3,0,3"/>
   <wangtile tileid="199" wangid="0,3,0,3,0,0,0,3"/>
   <wangtile tileid="200" wangid="0,3,0,3,0,3,0,3"/>
   <wangtile tileid="262" wangid="0,0,0,3,0,3,0,3"/>
   <wangtile tileid="263" wangid="0,3,0,3,0,3,0,0"/>
   <wangtile tileid="264" wangid="0,3,0,3,0,3,0,3"/>
   <wangtile tileid="326" wangid="0,0,0,2,0,0,0,0"/>
   <wangtile tileid="327" wangid="0,0,0,2,0,2,0,0"/>
   <wangtile tileid="328" wangid="0,0,0,0,0,2,0,0"/>
   <wangtile tileid="384" wangid="0,1,0,0,0,1,0,1"/>
   <wangtile tileid="385" wangid="0,1,0,1,0,0,0,1"/>
   <wangtile tileid="388" wangid="0,2,0,0,0,2,0,2"/>
   <wangtile tileid="389" wangid="0,2,0,2,0,0,0,2"/>
   <wangtile tileid="390" wangid="0,2,0,2,0,0,0,0"/>
   <wangtile tileid="391" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="392" wangid="0,0,0,0,0,2,0,2"/>
   <wangtile tileid="448" wangid="0,0,0,1,0,1,0,1"/>
   <wangtile tileid="449" wangid="0,1,0,1,0,1,0,0"/>
   <wangtile tileid="452" wangid="0,0,0,2,0,2,0,2"/>
   <wangtile tileid="453" wangid="0,2,0,2,0,2,0,0"/>
   <wangtile tileid="454" wangid="0,2,0,0,0,0,0,0"/>
   <wangtile tileid="455" wangid="0,2,0,0,0,0,0,2"/>
   <wangtile tileid="456" wangid="0,0,0,0,0,0,0,2"/>
  </wangset>
 </wangsets>
</tileset>
