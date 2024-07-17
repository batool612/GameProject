package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.world.Cell;
import model.world.CharacterCell;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class Tests {

	String characterPath = "model.characters.Character";
	String collectiblePath = "model.collectibles.Collectible";
	String vaccinePath = "model.collectibles.Vaccine";
	String supplyPath = "model.collectibles.Supply";
	String fighterPath = "model.characters.Fighter";

	private String charCell = "model.world.CharacterCell";
	private String cellPath = "model.world.Cell";
	private String collectibleCellPath = "model.world.CollectibleCell";
	private String trapCellPath = "model.world.TrapCell";
	private String enumDirectionPath = "model.characters.Direction";

	String gamePath = "engine.Game";
	String medicPath = "model.characters.Medic";
	String explorerPath = "model.characters.Explorer";
	String heroPath = "model.characters.Hero";

	String gameActionExceptionPath = "exceptions.GameActionException";
	String invalidTargetExceptionPath = "exceptions.InvalidTargetException";
	String movementExceptionPath = "exceptions.MovementException";
	String noAvailableResourcesExceptionPath = "exceptions.NoAvailableResourcesException";
	String notEnoughActionsExceptionPath = "exceptions.NotEnoughActionsException";

	String zombiePath = "model.characters.Zombie";
	String venomPath = "model.characters.Venom";
	String spitterPath = "model.characters.Spitter";
	String brutePath = "model.characters.Brute";

	// Helper method to get a point that is exactly range away of a point
	public Point getInRangePoint(Point p, int range, Cell[][] map)
	{
		int x = p.x;
		int y = p.y;
		int x1 = x - range;
		int x2 = x + range;
		int y1 = y - range;
		int y2 = y + range;
		if (x1 < 0)
			x1 = 0;
		if (x2 >= Game.map.length)
			x2 = Game.map.length - 1;
		if (y1 < 0)
			y1 = 0;
		if (y2 >= Game.map[0].length)
			y2 = Game.map[0].length - 1;
		int xr, yr;
		if(Math.abs(x1-p.x) > Math.abs(x2-p.x))
			xr = x1;
		else
			xr = x2;
		if(Math.abs(y1-p.y) > Math.abs(y2-p.y))
			yr = y1;
		else
			yr = y2;
		return new Point(xr, yr);
	}

	@Test(timeout = 100)
	public void testAttackRangeVenom() throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NotEnoughActionsException, InvalidTargetException
	{
		Hero ha = new Medic("John", 100, 20, 3);
		Game.availableHeroes.add(ha);
		Hero h = Game.availableHeroes.get(0);
		Game.startGame(h);
		for(Zombie z : Game.zombies)
		{
			Point p = z.getLocation();
			Game.map[p.x][p.y] = new CharacterCell(null);
		}
		Game.zombies.clear();
		Class<?> cv = null;
		Class<?> cz = null;
		Class<?> cc = null;
		try {
			cv = Class.forName(venomPath);
			cz = Class.forName(zombiePath);
			cc = Class.forName(characterPath);
		} catch (ClassNotFoundException e) {
			assertTrue("Class Venom not found", false);
			return;
		}
		Constructor c = null;
		try {
			c = cv.getConstructor();
		} catch (Exception e) {
			assertTrue("Constructor Venom() not found", false);
			return;
		}
		Zombie z = null;
		z = (Zombie)c.newInstance();
		Game.zombies.add(z);
		z.setLocation(getInRangePoint(h.getLocation(), 3, Game.map));
		Game.map[z.getLocation().x][z.getLocation().y] = new CharacterCell(z);
		int hp = h.getCurrentHp();
		Game.endTurn();
		assertEquals("Venom should have attacked the Hero when within range", hp - z.getAttackDmg() - 10, h.getCurrentHp());
		Game.map[z.getLocation().x][z.getLocation().y] = new CharacterCell(null);
		Game.zombies.clear();
		Game.zombies.add(z);
		z.setLocation(getInRangePoint(h.getLocation(), 2, Game.map));
		Game.map[z.getLocation().x][z.getLocation().y] = new CharacterCell(z);
		hp = h.getCurrentHp();
		Game.endTurn();
		assertEquals("Venom should have attacked the Hero when within range", hp - z.getAttackDmg() - 10, h.getCurrentHp());
		ha = new Medic("John", 100, 20, 3);
		Game.availableHeroes.add(ha);
		h = Game.availableHeroes.get(0);
		Game.startGame(h);
		for(Zombie zr : Game.zombies)
		{
			Point p = zr.getLocation();
			Game.map[p.x][p.y] = new CharacterCell(null);
		}
		Game.zombies.clear();
		z = (Zombie)c.newInstance();
		Game.zombies.add(z);
		z.setLocation(getInRangePoint(h.getLocation(), 4, Game.map));
		Game.map[z.getLocation().x][z.getLocation().y] = new CharacterCell(z);
		hp = h.getCurrentHp();
		Game.endTurn();
		assertEquals("Venom shouldn't have attacked the Hero when outside of their range", hp, h.getCurrentHp());

	}

	@Test(timeout = 100)
	public void testAttackRangeBrute() throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NotEnoughActionsException, InvalidTargetException
	{
		Hero ha = new Medic("John", 100, 20, 3);
		Game.availableHeroes.add(ha);
		Hero h = Game.availableHeroes.get(0);
		Game.startGame(h);
		for(Zombie z : Game.zombies)
		{
			Point p = z.getLocation();
			Game.map[p.x][p.y] = new CharacterCell(null);
		}
		Game.zombies.clear();
		Class<?> cb = null;
		Class<?> cz = null;
		Class<?> cc = null;
		try {
			cb = Class.forName(brutePath);
			cz = Class.forName(zombiePath);
			cc = Class.forName(characterPath);
		} catch (ClassNotFoundException e) {
			assertTrue("Class Brute not found", false);
			return;
		}
		Constructor c = null;
		try {
			c = cb.getConstructor();
		} catch (Exception e) {
			assertTrue("Constructor Brute() not found", false);
			return;
		}
		Zombie z = null;
		z = (Zombie)c.newInstance();
		Game.zombies.add(z);
		z.setLocation(getInRangePoint(h.getLocation(), 1, Game.map));
		Game.map[z.getLocation().x][z.getLocation().y] = new CharacterCell(z);
		int hp = h.getCurrentHp();
		Game.endTurn();
		assertEquals("Brute should have attacked the Hero when within range", hp - z.getAttackDmg(), h.getCurrentHp());
		Game.map[z.getLocation().x][z.getLocation().y] = new CharacterCell(null);
		Game.zombies.clear();
		Game.zombies.add(z);
		z.setLocation(getInRangePoint(h.getLocation(), 2, Game.map));
		Game.map[z.getLocation().x][z.getLocation().y] = new CharacterCell(z);
		hp = h.getCurrentHp();
		Game.endTurn();
		assertEquals("Brute shouldn't have attacked the Hero when outside of their range", hp, h.getCurrentHp());

	}

	@Test(timeout = 100)
	public void testAttackRangeSpitter() throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NotEnoughActionsException, InvalidTargetException
	{
		Hero ha = new Medic("John", 100, 20, 3);
		Game.availableHeroes.add(ha);
		Hero h = Game.availableHeroes.get(0);
		Game.startGame(h);
		for(Zombie z : Game.zombies)
		{
			Point p = z.getLocation();
			Game.map[p.x][p.y] = new CharacterCell(null);
		}
		Game.zombies.clear();
		Class<?> cs = null;
		Class<?> cz = null;
		Class<?> cc = null;
		try {
			cs = Class.forName(spitterPath);
			cz = Class.forName(zombiePath);
			cc = Class.forName(characterPath);
		} catch (ClassNotFoundException e) {
			assertTrue("Class Spitter not found", false);
			return;
		}
		Constructor c = null;
		try {
			c = cs.getConstructor();
		} catch (Exception e) {
			assertTrue("Constructor Spitter() not found", false);
			return;
		}
		Zombie z = null;
		z = (Zombie)c.newInstance();
		Game.zombies.add(z);
		z.setLocation(getInRangePoint(h.getLocation(), 6, Game.map));
		Game.map[z.getLocation().x][z.getLocation().y] = new CharacterCell(z);
		int hp = h.getCurrentHp();
		Game.endTurn();
		assertEquals("Spitter should have attacked the Hero when within range", hp - z.getAttackDmg(), h.getCurrentHp());
		Game.map[z.getLocation().x][z.getLocation().y] = new CharacterCell(null);
		Game.zombies.clear();
		Game.zombies.add(z);
		z.setLocation(getInRangePoint(h.getLocation(), 4, Game.map));
		Game.map[z.getLocation().x][z.getLocation().y] = new CharacterCell(z);
		hp = h.getCurrentHp();
		Game.endTurn();
		assertEquals("Spitter should have attacked the Hero when within range", hp - z.getAttackDmg(), h.getCurrentHp());
		Game.map[z.getLocation().x][z.getLocation().y] = new CharacterCell(null);
		Game.zombies.clear();
		Game.zombies.add(z);
		z.setLocation(getInRangePoint(h.getLocation(), 7, Game.map));
		Game.map[z.getLocation().x][z.getLocation().y] = new CharacterCell(z);
		hp = h.getCurrentHp();
		Game.endTurn();
		assertEquals("Spitter shouldn't have attacked the Hero when outside of their range", hp, h.getCurrentHp());

	}

	@Test(timeout = 100)
	public void testHeroPoisonedEndTurnEffect() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NotEnoughActionsException, InvalidTargetException
	{
		Hero ha = new Medic("John", 100, 20, 3);
		Game.availableHeroes.add(ha);
		Class<?> ch = null;
		try {
			ch = Class.forName(heroPath);
		} catch (ClassNotFoundException e) {
			assertTrue("Class Hero not found", false);
			return;
		}
		Hero h = Game.availableHeroes.get(0);
		Method mg = null;
		Method ms = null;
		try {
			mg = ch.getDeclaredMethod("getPoisoned");
		} catch (Exception e) {
			assertTrue("poisoned is a READ variable", false);
			return;
		}
		// check that poisoned does not have a setter
		try {
			ms = ch.getDeclaredMethod("setPoisoned", int.class);
		} catch (Exception e) {
			assertTrue("poisoned is a WRITE variable", false);
			return;
		}
		int hp = h.getCurrentHp();
		ms.invoke(h, 3);
		Game.startGame(h);
		for(Zombie z : Game.zombies)
		{
			Point p = z.getLocation();
			Game.map[p.x][p.y] = new CharacterCell(null);
		}
		Game.zombies.clear();
		Game.endTurn();
		assertEquals("Hero should lose 10 hp per turn while poisoned", hp - 10, h.getCurrentHp());
		int poisoned = (int) mg.invoke(h);
		assertEquals("Hero poisoned status shoud decay each turn", 2, poisoned);
	}

	@Test(timeout = 100)
	public void testHeroPoisonedEndTurnEffectIfNotPoisoned() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NotEnoughActionsException, InvalidTargetException
	{
		Hero ha = new Medic("John", 100, 20, 3);
		Game.availableHeroes.add(ha);
		Class<?> ch = null;
		try {
			ch = Class.forName(heroPath);
		} catch (ClassNotFoundException e) {
			assertTrue("Class Hero not found", false);
			return;
		}
		Hero h = Game.availableHeroes.get(0);
		Method mg = null;
		Method ms = null;
		try {
			mg = ch.getDeclaredMethod("getPoisoned");
		} catch (Exception e) {
			assertTrue("poisoned is a READ variable", false);
			return;
		}
		// check that poisoned does not have a setter
		try {
			ms = ch.getDeclaredMethod("setPoisoned", int.class);
		} catch (Exception e) {
			assertTrue("poisoned is a WRITE variable", false);
			return;
		}
		int hp = h.getCurrentHp();
		ms.invoke(h, 0);
		Game.startGame(h);
		for(Zombie z : Game.zombies)
		{
			Point p = z.getLocation();
			Game.map[p.x][p.y] = new CharacterCell(null);
		}
		Game.zombies.clear();
		Game.endTurn();
		assertEquals("Hero shouldn't lose hp if they aren't poisoned", hp, h.getCurrentHp());
		int poisoned = (int) mg.invoke(h);
		assertEquals("Hero poisoned status shoud decay to a minimum of 0", 0, poisoned);
	}

	@Test(timeout = 100)
	public void testHeroPoisonedAttribute()
	{
		Class<?> ch = null;
		try {
			ch = Class.forName(heroPath);
		} catch (ClassNotFoundException e) {
			assertTrue("Class Hero not found", false);
			return;
		}
		Field f = null;
		try {
			f = ch.getDeclaredField("poisoned");
		} catch (Exception e) {
			assertTrue("poisoned attribute not found", false);
			return;
		}
		// check that poisoned is private
		assertTrue("poisoned attribute should be private", java.lang.reflect.Modifier.isPrivate(f.getModifiers()));
		// check that poisoned has a getter
		Method m = null;
		// check that poisoned does have a setter
		try {
			m = ch.getDeclaredMethod("setPoisoned", int.class);
		} catch (Exception e) {
			assertTrue("poisoned is a WRITE variable", false);
			return;
		}
		try {
			m = ch.getDeclaredMethod("getPoisoned");
		} catch (Exception e) {
			assertTrue("poisoned is a READ variable", false);
			return;
		}
		Hero h = new Fighter("TestHero", 0, 0, 0);
		try {
			int poisoned = (int) m.invoke(h);
			assertEquals("poisoned was incorrectly initialized", 0, poisoned);
		} catch (Exception e) {
			assertTrue("poisoned is a WRITE variable", false);
			return;
		}
	}


	@Test(timeout = 100)
	public void testZombieIsAbstract()
	{
		Class<?> cz = null;
		try {
			cz = Class.forName(zombiePath);
		} catch (ClassNotFoundException e) {
			assertTrue("Class Zombie not found", false);
			return;
		}
		assertTrue("Zombie should be abstract", java.lang.reflect.Modifier.isAbstract(cz.getModifiers()));
	}

	@Test(timeout = 100)
	public void testZombieRangeAttribute()
	{
		Class<?> cz = null;
		try {
			cz = Class.forName(zombiePath);
		} catch (ClassNotFoundException e) {
			assertTrue("Class Zombie not found", false);
			return;
		}
		Field f = null;
		try {
			f = cz.getDeclaredField("range");
		} catch (Exception e) {
			assertTrue("range attribute not found", false);
			return;
		}
		// check that range is private
		assertTrue("range attribute should be private", java.lang.reflect.Modifier.isPrivate(f.getModifiers()));
		// check that range has a getter
		Method m = null;
		try {
			m = cz.getDeclaredMethod("getRange");
		} catch (Exception e) {
			assertTrue("range is a READ variable", false);
			return;
		}
		// check that range does not have a setter
		try {
			m = cz.getDeclaredMethod("setRange", int.class);
			assertTrue("range is a READ-ONLY variable", false);
		} catch (Exception e) {
			assertTrue(true);
			return;
		}
	}

	@Test(timeout = 100)
	public void testBruteAttributesAndIntialization() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException
	{
		Class<?> cb = null;
		Class<?> cz = null;
		Class<?> cc = null;
		try {
			cb = Class.forName(brutePath);
			cz = Class.forName(zombiePath);
			cc = Class.forName(characterPath);
		} catch (ClassNotFoundException e) {
			assertTrue("Class Brute not found", false);
			return;
		}
		Constructor c = null;
		try {
			c = cb.getConstructor();
		} catch (Exception e) {
			assertTrue("Constructor Brute() not found", false);
			return;
		}
		Object o = null;
		o = c.newInstance();
		Field f = null;
		f = cc.getDeclaredField("currentHp");
		f.setAccessible(true);
		int hp = (int) f.get(o);
		assertEquals("Brute hp initiallized incorrectly", 80, hp);

		f = cc.getDeclaredField("attackDmg");
		f.setAccessible(true);
		int damage = (int) f.get(o);
		assertEquals("Brute attack damage initiallized incorrectly", 4, damage);

		try{
			f = cz.getDeclaredField("range");
		}catch(Exception e){
			assertTrue("range attribute not found", false);
			return;
		}
		f.setAccessible(true);
		int range = (int) f.get(o);
		assertEquals("Brute range initiallized incorrectly", 1, range);
	}

	@Test(timeout = 100)
	public void testVenomAttributesAndIntialization() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException
	{
		Class<?> cv = null;
		Class<?> cz = null;
		Class<?> cc = null;
		try {
			cv = Class.forName(venomPath);
			cz = Class.forName(zombiePath);
			cc = Class.forName(characterPath);
		} catch (ClassNotFoundException e) {
			assertTrue("Class Venom not found", false);
			return;
		}
		Constructor c = null;
		try {
			c = cv.getConstructor();
		} catch (Exception e) {
			assertTrue("Constructor Venom() not found", false);
			return;
		}
		Object o = null;
		o = c.newInstance();
		Field f = null;
		f = cc.getDeclaredField("currentHp");
		f.setAccessible(true);
		int hp = (int) f.get(o);
		assertEquals("Venom Hp initiallized incorrectly", 40, hp);

		f = cc.getDeclaredField("attackDmg");
		f.setAccessible(true);
		int damage = (int) f.get(o);
		assertEquals("Venom attack damage initiallized incorrectly", 1, damage);

		try{
			f = cz.getDeclaredField("range");
		}catch(Exception e){
			assertTrue("range attribute not found", false);
			return;
		}
		f.setAccessible(true);
		int range = (int) f.get(o);
		assertEquals("Venom range initiallized incorrectly", 3, range);
	}

	@Test(timeout = 100)
	public void testSpitterAttributesAndIntialization() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, SecurityException
	{
		Class<?> cs = null;
		Class<?> cz = null;
		Class<?> cc = null;
		try {
			cs = Class.forName(spitterPath);
			cz = Class.forName(zombiePath);
			cc = Class.forName(characterPath);
		} catch (ClassNotFoundException e) {
			assertTrue("Class Spitter not found", false);
			return;
		}
		Constructor c = null;
		try {
			c = cs.getConstructor();
		} catch (Exception e) {
			assertTrue("Constructor Spitter() not found", false);
			return;
		}
		Object o = null;
		o = c.newInstance();
		Field f = null;
		f = cc.getDeclaredField("currentHp");
		f.setAccessible(true);
		int hp = (int) f.get(o);
		assertEquals("Spitter Hp initiallized incorrectly", 20, hp);

		f = cc.getDeclaredField("attackDmg");
		f.setAccessible(true);
		int damage = (int) f.get(o);
		assertEquals("Spitter attack damage initiallized incorrectly", 6, damage);

		try{
			f = cz.getDeclaredField("range");
		}catch(Exception e){
			assertTrue("range attribute not found", false);
			return;
		}
		f.setAccessible(true);
		int range = (int) f.get(o);
		assertEquals("Spitter range initiallized incorrectly", 6, range);
	}

	@Test(timeout = 1000)
	public void testAllZombieTypesSpawn()
	{
		Class bruteClass = null;
		Class venomClass = null;
		Class spitterClass = null;
		try {
			bruteClass = Class.forName(brutePath);
			venomClass = Class.forName(venomPath);
			spitterClass = Class.forName(spitterPath);
		} catch (ClassNotFoundException e) {
			assertTrue("A Class (Brute, Venom or Spitter) not found", false);
			return;
		}
		int tries = 1000;
		int bruteCount = 0;
		int venomCount = 0;
		int spitterCount = 0;
		for(int i = 0; i < tries; i++)
		{
			Hero ha = new Medic("John", 100, 20, 3);
			Game.availableHeroes.add(ha);
			Game.startGame(ha);
			for (Cell[] element : Game.map) {
				for(int x = 0; x < element.length; x++)
				{
					if(element[x] instanceof CharacterCell)
					{
						CharacterCell cc = (CharacterCell) element[x];
						if(cc.getCharacter() != null && cc.getCharacter().getClass().isAssignableFrom(bruteClass))
						{
							bruteCount++;
						}
						else if(cc.getCharacter() != null && cc.getCharacter().getClass().isAssignableFrom(venomClass))
						{
							venomCount++;
						}
						else if(cc.getCharacter() != null && cc.getCharacter().getClass().isAssignableFrom(spitterClass))
						{
							spitterCount++;
						}
					}
				}
			}
		}
		assertTrue("Brute zombie not spawned", bruteCount > 0);
		assertTrue("Venom zombie not spawned", venomCount > 0);
		assertTrue("Spitter zombie not spawned", spitterCount > 0);
	}
}