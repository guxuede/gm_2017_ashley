<?xml version="1.0" encoding="UTF-8"?>
<tileset version="1.10" tiledversion="1.11.0" name="terrain" tilewidth="32" tileheight="32" tilecount="1024" columns="32">
 <image source="terrain.png" width="1024" height="1024"/>
 <tile id="124">
  <animation>
   <frame tileid="124" duration="300"/>
   <frame tileid="188" duration="300"/>
   <frame tileid="187" duration="300"/>
   <frame tileid="189" duration="300"/>
  </animation>
 </tile>
 <wangsets>
  <wangset name="rock" type="corner" tile="97">
   <wangcolor name="light rock" color="#ff0000" tile="100" probability="1"/>
   <wangcolor name="mid light rock" color="#00ff00" tile="100" probability="1"/>
   <wangcolor name="wet rock" color="#0000ff" tile="103" probability="1"/>
   <wangtile tileid="1" wangid="0,1,0,0,0,1,0,1"/>
   <wangtile tileid="2" wangid="0,1,0,1,0,0,0,1"/>
   <wangtile tileid="4" wangid="0,2,0,0,0,2,0,2"/>
   <wangtile tileid="5" wangid="0,2,0,2,0,0,0,2"/>
   <wangtile tileid="7" wangid="0,3,0,0,0,3,0,3"/>
   <wangtile tileid="8" wangid="0,3,0,3,0,0,0,3"/>
   <wangtile tileid="33" wangid="0,0,0,1,0,1,0,1"/>
   <wangtile tileid="34" wangid="0,1,0,1,0,1,0,0"/>
   <wangtile tileid="36" wangid="0,0,0,2,0,2,0,2"/>
   <wangtile tileid="37" wangid="0,2,0,2,0,2,0,0"/>
   <wangtile tileid="39" wangid="0,0,0,3,0,3,0,3"/>
   <wangtile tileid="40" wangid="0,3,0,3,0,3,0,0"/>
   <wangtile tileid="64" wangid="0,0,0,1,0,0,0,0"/>
   <wangtile tileid="65" wangid="0,0,0,1,0,1,0,0"/>
   <wangtile tileid="66" wangid="0,0,0,0,0,1,0,0"/>
   <wangtile tileid="67" wangid="0,0,0,2,0,0,0,0"/>
   <wangtile tileid="68" wangid="0,0,0,2,0,2,0,0"/>
   <wangtile tileid="69" wangid="0,0,0,0,0,2,0,0"/>
   <wangtile tileid="70" wangid="0,0,0,3,0,0,0,0"/>
   <wangtile tileid="71" wangid="0,0,0,3,0,3,0,0"/>
   <wangtile tileid="72" wangid="0,0,0,0,0,3,0,0"/>
   <wangtile tileid="96" wangid="0,1,0,1,0,0,0,0"/>
   <wangtile tileid="97" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="98" wangid="0,0,0,0,0,1,0,1"/>
   <wangtile tileid="99" wangid="0,2,0,2,0,0,0,0"/>
   <wangtile tileid="100" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="101" wangid="0,0,0,0,0,2,0,2"/>
   <wangtile tileid="102" wangid="0,3,0,3,0,0,0,0"/>
   <wangtile tileid="103" wangid="0,3,0,3,0,3,0,3"/>
   <wangtile tileid="104" wangid="0,0,0,0,0,3,0,3"/>
   <wangtile tileid="128" wangid="0,1,0,0,0,0,0,0"/>
   <wangtile tileid="129" wangid="0,1,0,0,0,0,0,1"/>
   <wangtile tileid="130" wangid="0,0,0,0,0,0,0,1"/>
   <wangtile tileid="131" wangid="0,2,0,0,0,0,0,0"/>
   <wangtile tileid="132" wangid="0,2,0,0,0,0,0,2"/>
   <wangtile tileid="133" wangid="0,0,0,0,0,0,0,2"/>
   <wangtile tileid="134" wangid="0,3,0,0,0,0,0,0"/>
   <wangtile tileid="135" wangid="0,3,0,0,0,0,0,3"/>
   <wangtile tileid="136" wangid="0,0,0,0,0,0,0,3"/>
   <wangtile tileid="160" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="161" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="162" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="163" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="164" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="165" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="166" wangid="0,3,0,3,0,3,0,3"/>
   <wangtile tileid="167" wangid="0,3,0,3,0,3,0,3"/>
   <wangtile tileid="168" wangid="0,3,0,3,0,3,0,3"/>
  </wangset>
  <wangset name="Gass" type="corner" tile="289">
   <wangcolor name="light glass" color="#ff0000" tile="289" probability="1"/>
   <wangcolor name="glass" color="#00ff00" tile="292" probability="1"/>
   <wangcolor name="dark glass" color="#0000ff" tile="295" probability="1"/>
   <wangtile tileid="193" wangid="0,1,0,0,0,1,0,1"/>
   <wangtile tileid="194" wangid="0,1,0,1,0,0,0,1"/>
   <wangtile tileid="196" wangid="0,2,0,0,0,2,0,2"/>
   <wangtile tileid="197" wangid="0,2,0,2,0,0,0,2"/>
   <wangtile tileid="199" wangid="0,3,0,0,0,3,0,3"/>
   <wangtile tileid="200" wangid="0,3,0,3,0,0,0,3"/>
   <wangtile tileid="225" wangid="0,0,0,1,0,1,0,1"/>
   <wangtile tileid="226" wangid="0,1,0,1,0,1,0,0"/>
   <wangtile tileid="228" wangid="0,0,0,2,0,2,0,2"/>
   <wangtile tileid="229" wangid="0,2,0,2,0,2,0,0"/>
   <wangtile tileid="231" wangid="0,0,0,3,0,3,0,3"/>
   <wangtile tileid="232" wangid="0,3,0,3,0,3,0,0"/>
   <wangtile tileid="256" wangid="0,0,0,1,0,0,0,0"/>
   <wangtile tileid="257" wangid="0,0,0,1,0,1,0,0"/>
   <wangtile tileid="258" wangid="0,0,0,0,0,1,0,0"/>
   <wangtile tileid="259" wangid="0,0,0,2,0,0,0,0"/>
   <wangtile tileid="260" wangid="0,0,0,2,0,2,0,0"/>
   <wangtile tileid="261" wangid="0,0,0,0,0,2,0,0"/>
   <wangtile tileid="262" wangid="0,0,0,3,0,0,0,0"/>
   <wangtile tileid="263" wangid="0,0,0,3,0,3,0,0"/>
   <wangtile tileid="264" wangid="0,0,0,0,0,3,0,0"/>
   <wangtile tileid="288" wangid="0,1,0,1,0,0,0,0"/>
   <wangtile tileid="289" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="290" wangid="0,0,0,0,0,1,0,1"/>
   <wangtile tileid="291" wangid="0,2,0,2,0,0,0,0"/>
   <wangtile tileid="292" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="293" wangid="0,0,0,0,0,2,0,2"/>
   <wangtile tileid="294" wangid="0,3,0,3,0,0,0,0"/>
   <wangtile tileid="295" wangid="0,3,0,3,0,3,0,3"/>
   <wangtile tileid="296" wangid="0,0,0,0,0,3,0,3"/>
   <wangtile tileid="320" wangid="0,1,0,0,0,0,0,0"/>
   <wangtile tileid="321" wangid="0,1,0,0,0,0,0,1"/>
   <wangtile tileid="322" wangid="0,0,0,0,0,0,0,1"/>
   <wangtile tileid="323" wangid="0,2,0,0,0,0,0,0"/>
   <wangtile tileid="324" wangid="0,2,0,0,0,0,0,2"/>
   <wangtile tileid="325" wangid="0,0,0,0,0,0,0,2"/>
   <wangtile tileid="326" wangid="0,3,0,0,0,0,0,0"/>
   <wangtile tileid="327" wangid="0,3,0,0,0,0,0,3"/>
   <wangtile tileid="328" wangid="0,0,0,0,0,0,0,3"/>
   <wangtile tileid="352" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="353" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="354" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="355" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="356" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="357" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="358" wangid="0,3,0,3,0,3,0,3"/>
   <wangtile tileid="359" wangid="0,3,0,3,0,3,0,3"/>
   <wangtile tileid="360" wangid="0,3,0,3,0,3,0,3"/>
  </wangset>
  <wangset name="Underground" type="corner" tile="106">
   <wangcolor name="Dark rock" color="#ff0000" tile="106" probability="1"/>
   <wangcolor name="Gary rock" color="#00ff00" tile="109" probability="1"/>
   <wangcolor name="lava" color="#0000ff" tile="112" probability="1"/>
   <wangtile tileid="10" wangid="0,1,0,0,0,1,0,1"/>
   <wangtile tileid="11" wangid="0,1,0,1,0,0,0,1"/>
   <wangtile tileid="13" wangid="0,2,0,0,0,2,0,2"/>
   <wangtile tileid="14" wangid="0,2,0,2,0,0,0,2"/>
   <wangtile tileid="16" wangid="0,3,0,0,0,3,0,3"/>
   <wangtile tileid="17" wangid="0,3,0,3,0,0,0,3"/>
   <wangtile tileid="42" wangid="0,0,0,1,0,1,0,1"/>
   <wangtile tileid="43" wangid="0,1,0,1,0,1,0,0"/>
   <wangtile tileid="45" wangid="0,0,0,2,0,2,0,2"/>
   <wangtile tileid="46" wangid="0,2,0,2,0,2,0,0"/>
   <wangtile tileid="48" wangid="0,0,0,3,0,3,0,3"/>
   <wangtile tileid="49" wangid="0,3,0,3,0,3,0,0"/>
   <wangtile tileid="73" wangid="0,0,0,1,0,0,0,0"/>
   <wangtile tileid="74" wangid="0,0,0,1,0,1,0,0"/>
   <wangtile tileid="75" wangid="0,0,0,0,0,1,0,0"/>
   <wangtile tileid="76" wangid="0,0,0,2,0,0,0,0"/>
   <wangtile tileid="77" wangid="0,0,0,2,0,2,0,0"/>
   <wangtile tileid="78" wangid="0,0,0,0,0,2,0,0"/>
   <wangtile tileid="79" wangid="0,0,0,3,0,0,0,0"/>
   <wangtile tileid="80" wangid="0,0,0,3,0,3,0,0"/>
   <wangtile tileid="81" wangid="0,0,0,0,0,3,0,0"/>
   <wangtile tileid="105" wangid="0,1,0,1,0,0,0,0"/>
   <wangtile tileid="106" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="107" wangid="0,0,0,0,0,1,0,1"/>
   <wangtile tileid="108" wangid="0,2,0,2,0,0,0,0"/>
   <wangtile tileid="109" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="110" wangid="0,0,0,0,0,2,0,2"/>
   <wangtile tileid="111" wangid="0,3,0,3,0,0,0,0"/>
   <wangtile tileid="112" wangid="0,3,0,3,0,3,0,3"/>
   <wangtile tileid="113" wangid="0,0,0,0,0,3,0,3"/>
   <wangtile tileid="137" wangid="0,1,0,0,0,0,0,0"/>
   <wangtile tileid="138" wangid="0,1,0,0,0,0,0,1"/>
   <wangtile tileid="139" wangid="0,0,0,0,0,0,0,1"/>
   <wangtile tileid="140" wangid="0,2,0,0,0,0,0,0"/>
   <wangtile tileid="141" wangid="0,2,0,0,0,0,0,2"/>
   <wangtile tileid="142" wangid="0,0,0,0,0,0,0,2"/>
   <wangtile tileid="143" wangid="0,3,0,0,0,0,0,0"/>
   <wangtile tileid="144" wangid="0,3,0,0,0,0,0,3"/>
   <wangtile tileid="145" wangid="0,0,0,0,0,0,0,3"/>
   <wangtile tileid="169" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="170" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="171" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="172" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="173" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="174" wangid="0,2,0,2,0,2,0,2"/>
   <wangtile tileid="175" wangid="0,3,0,3,0,3,0,3"/>
   <wangtile tileid="176" wangid="0,3,0,3,0,3,0,3"/>
   <wangtile tileid="177" wangid="0,3,0,3,0,3,0,3"/>
  </wangset>
  <wangset name="river" type="corner" tile="27">
   <wangcolor name="River" color="#ff0000" tile="59" probability="1"/>
   <wangtile tileid="28" wangid="0,1,0,0,0,1,0,1"/>
   <wangtile tileid="29" wangid="0,1,0,1,0,0,0,1"/>
   <wangtile tileid="60" wangid="0,0,0,1,0,1,0,1"/>
   <wangtile tileid="61" wangid="0,1,0,1,0,1,0,0"/>
   <wangtile tileid="91" wangid="0,0,0,1,0,0,0,0"/>
   <wangtile tileid="92" wangid="0,0,0,1,0,1,0,0"/>
   <wangtile tileid="93" wangid="0,0,0,0,0,1,0,0"/>
   <wangtile tileid="123" wangid="0,1,0,1,0,0,0,0"/>
   <wangtile tileid="124" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="125" wangid="0,0,0,0,0,1,0,1"/>
   <wangtile tileid="155" wangid="0,1,0,0,0,0,0,0"/>
   <wangtile tileid="156" wangid="0,1,0,0,0,0,0,1"/>
   <wangtile tileid="157" wangid="0,0,0,0,0,0,0,1"/>
   <wangtile tileid="187" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="188" wangid="0,1,0,1,0,1,0,1"/>
   <wangtile tileid="189" wangid="0,1,0,1,0,1,0,1"/>
  </wangset>
 </wangsets>
</tileset>
